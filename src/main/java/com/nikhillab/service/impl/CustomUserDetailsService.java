package com.nikhillab.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nikhillab.repo.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepo userRepo;

	public CustomUserDetailsService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));

	}

}
