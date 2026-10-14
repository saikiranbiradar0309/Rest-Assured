package com.oauth2.api.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oauth2.api.config.OAuth2Properties;
import com.oauth2.api.dto.UserRequest;
import com.oauth2.api.dto.UserResponse;
import com.oauth2.api.entity.Role;
import com.oauth2.api.entity.User;
import com.oauth2.api.exception.DuplicateEmailException;
import com.oauth2.api.exception.DuplicateUsernameException;
import com.oauth2.api.exception.RoleNotFoundException;
import com.oauth2.api.exception.UserNotFoundException;
import com.oauth2.api.repository.RoleRepository;
import com.oauth2.api.repository.UserRepository;

@Service
public class UserService {

	private final OAuth2Properties OAuth2Properties;

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder,
			OAuth2Properties OAuth2Properties) {

		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.OAuth2Properties = OAuth2Properties;
	}

	@Transactional
	public UserResponse assignRole(Long userId, Long roleId) {

		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

		Role role = roleRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException("Role not found"));

		user.getRoles().add(role);

		userRepository.save(user);

		return map(user);
	}

	@Transactional
	public UserResponse removeRole(Long userId, Long roleId) {

		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

		user.getRoles().removeIf(role -> role.getId().equals(roleId));

		userRepository.save(user);

		return map(user);
	}

	public List<UserResponse> findAll() {

		return userRepository.findAll()

				.stream()

				.map(this::map)

				.toList();

	}

	public UserResponse findById(Long id) {

		User user = userRepository.findById(id)

				.orElseThrow(() -> new UserNotFoundException("User not found"));

		return map(user);

	}

	public void delete(Long id) {

		User user = userRepository.findById(id)

				.orElseThrow(() -> new UserNotFoundException("User not found"));

		userRepository.delete(user);

	}

	public UserResponse update(Long id, UserRequest request) {

		User user = userRepository.findById(id)

				.orElseThrow(() -> new UserNotFoundException("User not found"));

		user.setEmail(request.getEmail());

		user.setFirstName(request.getFirstName());

		user.setLastName(request.getLastName());

		user.setEnabled(true);

		userRepository.save(user);

		return map(user);

	}

	public UserResponse create(UserRequest request) {

		System.out.println("Calling Service Layer...");
		if (userRepository.existsByUsername(request.getUsername())) {
			throw new DuplicateUsernameException();
		}

		if (userRepository.existsByEmail(request.getEmail())) {
			throw new DuplicateEmailException();
		}

		Set<Role> roles = request.getRoles()

				.stream()

				.map(role -> roleRepository.findByRoleName(role)

						.orElseThrow(RoleNotFoundException::new))

				.collect(Collectors.toSet());

		User user = new User();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setEnabled(true);
		user.setRoles(roles);

		userRepository.save(user);

		return map(user);

	}

	private UserResponse map(User user) {

		UserResponse userResponse = new UserResponse();

		userResponse.setId(user.getId());

		userResponse.setFirstName(user.getFirstName());

		userResponse.setLastName(user.getLastName());

		userResponse.setEmail(user.getEmail());

		userResponse.setUsername(user.getUsername());

		userResponse.setEnabled(user.getEnabled());

		userResponse.setRoles(user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toSet()));

		return userResponse;

	}

}