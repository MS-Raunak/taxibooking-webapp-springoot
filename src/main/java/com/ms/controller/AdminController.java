package com.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ms.model.Admin;
import com.ms.model.ServiceForm;
import com.ms.service.AdminCredentialsService;
import com.ms.service.BookingFormService;
import com.ms.service.ContactFormService;
import com.ms.service.ServiceFormService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	ContactFormService contactFormService;
	@Autowired
	AdminCredentialsService adminCredentialsService;
	@Autowired
	BookingFormService bookingFormService;
	@Autowired
	ServiceFormService serviceFormService;
	
	
	
	@GetMapping("/dashboard")
	public String adminDashboard() {
		return "admin/dashboard";
	}
	
	@GetMapping("readAllContacts")
	public String readAllContacts(Model model) {
		model.addAttribute("allContacts", contactFormService.readAllContactService());
		return "admin/allContacts";
	}
	
	@GetMapping("deleteContact/{id}")
	public String deleteContacts(@PathVariable int id, RedirectAttributes redirectAttributes) {
		contactFormService.deleteContactService(id);
		redirectAttributes.addAttribute("deleteMsg", "Contact deleted successfully");
		return "redirect:/admin/readAllContacts";
	}
	
	//Show form page to change username and password
	@GetMapping("/changeCredentialsView")
	public String changeCredentialsView() {
		return "admin/changeCredentials";
	}
	//write logic for changing username and password
	@PostMapping("/changeCredentials")
	public String changeCredentials(@RequestParam("oldUsername")String oldUsername,
									@RequestParam("oldPassword")String oldPassword,
									@RequestParam("newUsername")String newUsername,
									@RequestParam("newPassword")String newPassword,
									RedirectAttributes redirectAttributes) {
		
		String credentials = adminCredentialsService.checkAdminCredentials(oldUsername, oldPassword);
		System.out.println(credentials);
		if (credentials=="SUCCESS") {
			credentials= adminCredentialsService.updateAdminCredentials(newUsername, newPassword, oldUsername);
			redirectAttributes.addFlashAttribute("message", credentials);
		}
		else {
			redirectAttributes.addFlashAttribute("message", credentials);
		}
		  return "redirect:/admin/dashboard";
	}
	
	@GetMapping("readAllBookings")
	public String readAllBookings(Model model) {
		model.addAttribute("allBookings", bookingFormService.readAllBookingService());
		return "admin/allBookings";
	}
	
	@GetMapping("deleteBooking/{id}")
	public String deleteBooking(@PathVariable int id, RedirectAttributes redirectAttributes) {
		bookingFormService.deleteBookingService(id);
		redirectAttributes.addAttribute("deleteMsg", "Booking deleted successfully");
		return "redirect:/admin/readAllBookings";
	}
	
	@GetMapping("addService")
	public String addServiceView() {
		return "admin/addservice";
	}
	
	
	//This method  is used to disable while binding image object in service form object
	@InitBinder
	public void stopBinding(WebDataBinder webDataBinder ) {
		webDataBinder.setDisallowedFields("image");
	}
	
	@PostMapping("addService")
	public String addServicePost(@ModelAttribute ServiceForm serviceForm, @RequestParam("image") 
				MultipartFile multipartFile, RedirectAttributes redirectAttributes) {
		String originalFilename = multipartFile.getOriginalFilename();
		serviceForm.setImage(originalFilename);
		
		try {
			ServiceForm saveService = serviceFormService.addService(serviceForm, multipartFile);
			if (saveService!=null) {
				redirectAttributes.addFlashAttribute("succMsg", "service added successfully");
			}
			else {
				redirectAttributes.addFlashAttribute("errorMsg", "something went wrong");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMsg", "service added successfully");
		}
		
		return "redirect:/admin/addService";
	}
	


}
