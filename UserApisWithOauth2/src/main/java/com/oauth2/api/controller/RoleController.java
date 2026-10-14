package com.oauth2.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oauth2.api.dto.RoleRequest;
import com.oauth2.api.dto.RoleResponse;
import com.oauth2.api.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {

	private final RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@PostMapping("/{roleId}/permissions/{permissionId}")
	public RoleResponse assignPermission(@PathVariable Long roleId, @PathVariable Long permissionId) {

		return roleService.assignPermission(roleId, permissionId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RoleResponse create(@RequestBody RoleRequest request) {
		return roleService.save(request);
	}

	@DeleteMapping("/{roleId}/permissions/{permissionId}")
	public RoleResponse removePermission(@PathVariable Long roleId, @PathVariable Long permissionId) {

		return roleService.removePermission(roleId, permissionId);
	}

	@GetMapping
	public List<RoleResponse> findAll() {
		return roleService.findAll();
	}

	@GetMapping("/{id}")
	public RoleResponse findById(@PathVariable Long id) {
		return roleService.findById(id);
	}

	@PutMapping("/{id}")
	public RoleResponse update(@PathVariable Long id, @RequestBody RoleRequest request) {

		return roleService.update(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		roleService.delete(id);
	}
}