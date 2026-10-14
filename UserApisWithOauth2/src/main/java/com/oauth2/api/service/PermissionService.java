package com.oauth2.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oauth2.api.dto.PermissionRequest;
import com.oauth2.api.dto.PermissionResponse;
import com.oauth2.api.entity.Permission;
import com.oauth2.api.exception.DuplicatePermissionException;
import com.oauth2.api.exception.PermissionNotFoundException;
import com.oauth2.api.repository.PermissionRepository;

@Service
public class PermissionService {

	private final PermissionRepository repository;

	public PermissionService(PermissionRepository repository) {
		this.repository = repository;
	}

	public PermissionResponse create(PermissionRequest request) {

		if (repository.existsByPermissionName(request.getPermissionName())) {
			throw new DuplicatePermissionException("Permission already exists");
		}

		Permission permission = new Permission();

		permission.setPermissionName(request.getPermissionName());

		permission.setDescription(request.getDescription());

		permission = repository.save(permission);

		return map(permission);
	}

	public List<PermissionResponse> findAll() {

		return repository.findAll().stream().map(this::map).toList();
	}

	public PermissionResponse findById(Long id) {

		Permission permission = repository.findById(id)
				.orElseThrow(() -> new PermissionNotFoundException("Permission not found"));

		return map(permission);
	}

	public PermissionResponse update(Long id, PermissionRequest request) {

		Permission permission = repository.findById(id)
				.orElseThrow(() -> new PermissionNotFoundException("Permission not found"));

		permission.setPermissionName(request.getPermissionName());
		permission.setDescription(request.getDescription());

		permission = repository.save(permission);

		return map(permission);
	}

	public void delete(Long id) {

		Permission permission = repository.findById(id)
				.orElseThrow(() -> new PermissionNotFoundException("Permission not found"));

		repository.delete(permission);
	}

	private PermissionResponse map(Permission permission) {

		PermissionResponse response = new PermissionResponse();

		response.setId(permission.getId());

		response.setPermissionName(permission.getPermissionName());

		response.setDescription(permission.getDescription());

		return response;
	}
}