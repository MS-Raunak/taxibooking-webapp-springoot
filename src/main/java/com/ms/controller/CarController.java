package com.ms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CarController {
	
	@GetMapping(path = {"/", "/home", "/index"})
	public String homePage(HttpServletRequest request, Model model) {
		String requestURI = request.getRequestURI();
		model.addAttribute("mycurrentrequest", requestURI);
		return "index";
	}
	
	@GetMapping("/about")
	public String aboutPage(HttpServletRequest request, Model model) {
		String requestURI = request.getRequestURI();
		model.addAttribute("mycurrentrequest", requestURI);
		return "about";
	}
	
	@GetMapping("/cars")
	public String carPage(HttpServletRequest request, Model model) {
		String requestURI = request.getRequestURI();
		model.addAttribute("mycurrentrequest", requestURI);
		
		return "cars";
	}
	
	@GetMapping("/services")
	public String servicesPage(HttpServletRequest request, Model model) {
		String requestURI = request.getRequestURI();
		model.addAttribute("mycurrentrequest", requestURI);
		return "services";
	}

	@GetMapping("/contacts")
	public String contactPage(HttpServletRequest request, Model model) {
		String requestURI = request.getRequestURI();
		model.addAttribute("mycurrentrequest", requestURI);
		return "contacts";
	}
}
