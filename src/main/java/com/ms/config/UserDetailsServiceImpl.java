package com.ms.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ms.dao.AdminDao;
import com.ms.model.Admin;

import jakarta.annotation.PostConstruct;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PostConstruct
	private void init() {

		if(adminDao.count()==0) {
			Admin admin = new Admin();
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode("Admin123"));
			
			adminDao.save(admin);
		}

	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Admin> byUserName = adminDao.findByUsername(username);
		Admin admin = byUserName.orElseThrow(()-> new UsernameNotFoundException("Admin doesn't exist!"));
		return User.withUsername(admin.getUsername()).password(admin.getPassword()).build();
	}

}
