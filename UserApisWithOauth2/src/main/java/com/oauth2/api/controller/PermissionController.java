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

import com.oauth2.api.dto.PermissionRequest;
import com.oauth2.api.dto.PermissionResponse;
import com.oauth2.api.service.PermissionService;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PermissionResponse create(@RequestBody PermissionRequest request) {

        return permissionService.create(request);
    }

    @GetMapping
    public List<PermissionResponse> findAll() {

        return permissionService.findAll();
    }

    @GetMapping("/{id}")
    public PermissionResponse findById(@PathVariable Long id) {

        return permissionService.findById(id);
    }

    @PutMapping("/{id}")
    public PermissionResponse update(
            @PathVariable Long id,
            @RequestBody PermissionRequest request) {

        return permissionService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {

        permissionService.delete(id);
    }
}