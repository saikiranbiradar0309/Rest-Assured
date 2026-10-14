package com.oauth2.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oauth2.api.dto.RoleRequest;
import com.oauth2.api.dto.RoleResponse;
import com.oauth2.api.entity.Permission;
import com.oauth2.api.entity.Role;
import com.oauth2.api.exception.DuplicateRoleException;
import com.oauth2.api.exception.PermissionNotFoundException;
import com.oauth2.api.exception.RoleNotFoundException;
import com.oauth2.api.repository.PermissionRepository;
import com.oauth2.api.repository.RoleRepository;

@Service
public class RoleService {

	private final RoleRepository repository;
	private final PermissionRepository permissionRepository;

	public RoleService(RoleRepository repository, PermissionRepository permissionRepository) {

		this.repository = repository;
		this.permissionRepository = permissionRepository;
	}

	@Transactional
	public RoleResponse removePermission(Long roleId, Long permissionId) {

		Role role = repository.findById(roleId).orElseThrow(() -> new RoleNotFoundException("Role not found"));

		role.getPermissions().removeIf(p -> p.getId().equals(permissionId));

		repository.save(role);

		return map(role);
	}

	@Transactional
	public RoleResponse assignPermission(Long roleId, Long permissionId) {

		Role role = repository.findById(roleId).orElseThrow(() -> new RoleNotFoundException("Role not found"));

		Permission permission = permissionRepository.findById(permissionId)
				.orElseThrow(() -> new PermissionNotFoundException("Permission not found"));

		role.getPermissions().add(permission);

		repository.save(role);

		return map(role);
	}

	public RoleResponse save(RoleRequest request) {

		if (repository.existsByRoleName(request.getRoleName())) {
			throw new DuplicateRoleException("Role already exists");
		}

		Role role = new Role();

		role.setRoleName(request.getRoleName());

		role.setDescription(request.getDescription());

		role = repository.save(role);

		return map(role);
	}

	public List<RoleResponse> findAll() {

		return repository.findAll().stream().map(this::map).toList();
	}

	public RoleResponse findById(Long id) {

		Role role = repository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));

		return map(role);
	}

	public RoleResponse update(Long id, RoleRequest request) {

		Role role = repository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));

		role.setRoleName(request.getRoleName());
		role.setDescription(request.getDescription());

		role = repository.save(role);

		return map(role);
	}

	public void delete(Long id) {

		Role role = repository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));

		repository.delete(role);
	}

	private RoleResponse map(Role role) {

		RoleResponse response = new RoleResponse();

		response.setId(role.getId());

		response.setRoleName(role.getRoleName());

		response.setDescription(role.getDescription());

		return response;
	}
}