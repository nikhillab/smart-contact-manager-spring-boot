package com.nikhillab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nikhillab.helper.AuthFailtureHandler;
import com.nikhillab.service.impl.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

//	public UserDetailsService userDetailsService() {
//		var inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
//
//		// user
//		var userDetails = User.builder().username("admin").password("1234567").roles("ADMIN", "USER").build();
//
//		inMemoryUserDetailsManager.createUser(userDetails);
//
//		return inMemoryUserDetailsManager;
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService) {

		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

		return daoAuthenticationProvider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,AuthFailtureHandler authFailtureHandler) throws Exception {
		httpSecurity.authorizeHttpRequests(request -> {
			request.requestMatchers("/user/**").authenticated();
			request.anyRequest().permitAll();
//			request.requestMatchers("/home", "/register").permitAll();
		});

		// disable for now for logout
		httpSecurity.csrf(AbstractHttpConfigurer::disable);
		
		// change if we want to change login form
		httpSecurity.formLogin(form -> form.loginPage("/login").permitAll().loginProcessingUrl("/authenticate")
				.defaultSuccessUrl("/user/dashboard").failureUrl("/login?error=true").usernameParameter("email")
				.passwordParameter("password").failureHandler(authFailtureHandler));
		

		httpSecurity.logout(logout -> logout.logoutUrl("/logout").permitAll().logoutSuccessUrl("/login?logout=true"));

		return httpSecurity.build();
	}

}
