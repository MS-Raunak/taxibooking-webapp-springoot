package com.ms.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomLogoutHandler implements LogoutHandler{

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		try {
			//configure custom logout message, handle in controller login handler
			ServletContext servletContext = request.getServletContext();
			servletContext.setAttribute("logout", true);
			
			response.sendRedirect("admin/dashboard");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
