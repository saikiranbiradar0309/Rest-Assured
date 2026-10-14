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
import com.oauth2.api.config.OAuth2Properties;
import com.oauth2.api.dto.UserRequest;
import com.oauth2.api.dto.UserResponse;
import com.oauth2.api.service.UserService;

@RestController
public class UserController {

    private final OAuth2Properties OAuth2Properties;

    private final UserService userService;

    public UserController(UserService userService, OAuth2Properties OAuth2Properties) {
        this.userService = userService;
        this.OAuth2Properties = OAuth2Properties;
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody UserRequest request) {
    	
    	System.out.println(request);
    	
        return userService.create(request);
    }

    @PostMapping("/users/{userId}/roles/{roleId}")
    public UserResponse assignRole(
            @PathVariable Long userId,
            @PathVariable Long roleId) {

        return userService.assignRole(userId, roleId);
    }
    
    @DeleteMapping("/users/{userId}/roles/{roleId}")
    public UserResponse removeRole(
            @PathVariable Long userId,
            @PathVariable Long roleId) {

        return userService.removeRole(userId, roleId);
    }
    
    
    @GetMapping("/users")
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping("/users/{id}")
    public UserResponse update(
            @PathVariable Long id,
            @RequestBody UserRequest request) {

        return userService.update(id, request);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}