package com.ms.service;

public interface AdminCredentialsService {
	public String checkAdminCredentials(String oldUsername, String oldPassword);
	public String updateAdminCredentials(String newUsername, String newPassword, String oldUsername);
}
