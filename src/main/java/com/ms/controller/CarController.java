package com.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ms.model.BookingForm;
import com.ms.model.ContactForm;
import com.ms.model.ServiceForm;
import com.ms.service.BookingFormService;
import com.ms.service.ContactFormService;
import com.ms.service.ServiceFormService;

@Controller
public class CarController {
	ContactFormService contactFormService;
	@Autowired //Setter method Autowiring instead of fields wiring
	public void setContactFormService(ContactFormService contactFormService) {
		this.contactFormService = contactFormService;
	}
	
	BookingFormService bookingFormService;
	@Autowired
	public void setBookingFormService(BookingFormService bookingFormService) {
		this.bookingFormService = bookingFormService;
	}
	@Autowired
	ServiceFormService serviceFormService;
	
	
	
	

	@GetMapping(path = { "/", "/home", "/index" })
	public String homePage(HttpServletRequest request, Model model) {
		String requestURI = request.getRequestURI();
		/**
		 * HttpServletRequest- interface getRequestURI()- It is a method of
		 * HttpServletRequest is used to return current request Model- an interface
		 * which is used to send data from controller to view
		 */
		model.addAttribute("mycurrentrequest", requestURI);
		//model.addAttribute("bookingFormObj", new BookingForm());

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
		/**
		 * HttpServletRequest- interface getRequestURI()- It is a method of
		 * HttpServletRequest is used to return current request
		 */
		model.addAttribute("mycurrentrequest", requestURI);
		//model.addAttribute("bookingFormObj", new BookingForm());//might be mistake to write here that's why commented
		
		//Collect data
		List<ServiceForm> allServices = serviceFormService.readAllServices();
		model.addAttribute("allServices", allServices);
		return "services";
	}

	@GetMapping("/contacts")
	public String contactPage(HttpServletRequest request, Model model) {
		String requestURI = request.getRequestURI();
		/**
		 * HttpServletRequest- interface getRequestURI()- It is a method of
		 * HttpServletRequest is used to return current request Here we are sending
		 * current request to the view for styling purpose
		 */
		model.addAttribute("mycurrentrequest", requestURI);
		model.addAttribute("contactFormObj", new ContactForm());
		return "contacts";
	}

	@PostMapping("/contactForm")
	public String contactForm(@Valid @ModelAttribute("contactFormObj") ContactForm contactForm, 
								BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		/**
		 * @Valid - used to perform validation on fields in ContactForm class
		 * @ModelAttribute - used to get object from view after setting data (filled the form) 
		 * BindingResult - it is an interface which is used to get validation messages
		 * RedirectAttributes is working same as Model to send the data on view but Model works when
		 * we send direct request if request is breaking like :redirect:/demo: in that case we use RedirectAttributes
		 */
		if (bindingResult.hasErrors()) {
			model.addAttribute("bindingResult", bindingResult);
			System.out.println(contactForm);
			return "contacts";
		}
		System.out.println(contactForm);
		ContactForm contactFormObj = contactFormService.saveContactFormService(contactForm);
		
		if(contactForm!=null) {
			redirectAttributes.addFlashAttribute("message", "Message Sent Successfully");
		}
		else {
			redirectAttributes.addFlashAttribute("message", "Something went wrong");
		}
		return "redirect:/contacts"; // to avoid re-submission using redirect here
	}
	
	@GetMapping("/showBookingForm")
	public String showBookingForm(Model model ) {
		model.addAttribute("bookingFormObj", new BookingForm());
		return "bookingFormPage";
	}
	
	@PostMapping("/bookingForm")
	public String bookingForm(@Valid @ModelAttribute("bookingFormObj") BookingForm bookingForm, 
								BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("bindingResult", bindingResult);
			return "bookingFormPage";
		}
		else if(bookingForm.getAdult() + bookingForm.getChildren() > 4) {
			model.addAttribute("personExceed", "Maximum 4 person allowed in a taxi");
			return "bookingFormPage";
		}
		//service
		BookingForm saveBookingFormService = bookingFormService.saveBookingFormService(bookingForm);
		
		if(saveBookingFormService!=null) {
			redirectAttributes.addFlashAttribute("message", "Booking Sucessfully done..");
		}
		else {
			redirectAttributes.addFlashAttribute("message", "Something went wrong");
		}
		return "redirect:/showBookingForm"; 
	}
	
	//Showing custom login form or return custom logout message with custom login form
	@GetMapping("/login")
	public String getMethodName(HttpServletRequest request, Model model) {
		
		//Mapping for default logout message, coming from customLogoutHandler
		ServletContext servletContext = request.getServletContext();
		Object attribute = servletContext.getAttribute("logout");
		if(attribute instanceof Boolean) {
			model.addAttribute("logout", attribute);
			servletContext.removeAttribute("logout");
		}
		return "/adminLogin";
	}
	
}