package com.oauth2.api.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oauth2.api.entity.User;
import com.oauth2.api.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		User user = userRepository.findByUsername(username)
//				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//		return new CustomUserDetails(user);
//
//	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		System.out.println("====== USER FROM DATABASE ======");
		System.out.println("Username : " + user.getUsername());
		System.out.println("Password : " + user.getPassword());
		System.out.println("Enabled  : " + user.getEnabled());
		System.out.println("Roles    : " + user.getRoles().size());
		System.out.println("===============================");

		return new CustomUserDetails(user);
	}

}