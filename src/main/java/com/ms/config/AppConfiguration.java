package com.ms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
public class AppConfiguration {
	
	CustomLogoutHandler customLogoutHandler;
	 // Setter method for dependency injection of CustomLogoutHandler
	@Autowired
	public void setCustomLogoutHandler(CustomLogoutHandler customLogoutHandler) {
		this.customLogoutHandler = customLogoutHandler;
	}
	
	
	//Configuring HTTP security settings including authentication, authorization, and logout
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.csrf(csrf->csrf.disable()) 
		.authorizeHttpRequests(authorize->authorize
				.requestMatchers("/admin/**").authenticated() // Requires authentication for /admin/** endpoints
				.requestMatchers("/**").permitAll())         // Allows access to all other endpoints
		
		.formLogin(form-> form
				.loginPage("/login")// custom login form
				.permitAll()
		)                 // Allows access to the login form
		
		.logout(logout->logout.addLogoutHandler(customLogoutHandler)
				.logoutUrl("/doLogout") // Sets custom logout URL
		);

		
		return httpSecurity.build();
	}
	
	
	
	// Provides a PasswordEncoder bean
	@Bean
	PasswordEncoder passwordEncoder() {
		//return new BCryptPasswordEncoder();
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
