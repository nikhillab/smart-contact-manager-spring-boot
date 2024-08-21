package com.nikhillab;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nikhillab.entities.User;
import com.nikhillab.service.UserService;

@SpringBootApplication
public class SmartContactManager implements CommandLineRunner {
	
	@Autowired
	UserService service;

	public static void main(String[] args) {
		SpringApplication.run(SmartContactManager.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Optional<User> userById = service.getUserById(2l);
//		
//		System.out.println(userById.get().getProvider());
	}

}
