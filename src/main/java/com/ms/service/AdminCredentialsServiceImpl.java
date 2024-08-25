package com.ms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ms.dao.AdminDao;
import com.ms.model.Admin;

@Service
public class AdminCredentialsServiceImpl implements AdminCredentialsService{
	
	@Autowired
	AdminDao adminDao;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	Admin admin;
	
	@Override
	public String checkAdminCredentials(String oldUsername, String oldPassword) {
		Optional<Admin> byUserName = adminDao.findByUsername(oldUsername);
		if(byUserName.isPresent()) {
			admin = byUserName.get();
			boolean matches = passwordEncoder.matches(oldPassword, admin.getPassword());
			return matches ? "SUCCESS" : "Wrong Old Credentials";
		}
		else {
			return "Wrong Old Credentials";
		}
	}


	@Override
	public String updateAdminCredentials(String newUsername, String newPassword, String oldUsername) {
		int updateCredentials = adminDao.updateCredentials(newUsername, passwordEncoder.encode(newPassword), oldUsername);
		
		if (updateCredentials==1) {
			return "CREDENTIALS UPDATED SUCCESSFULLY";
		}
		else {
			return "FAILED TO UPDATE";
		}
	}
	


}
