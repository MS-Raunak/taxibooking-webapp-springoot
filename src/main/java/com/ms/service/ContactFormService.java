package com.ms.service;

import java.util.List;

import com.ms.model.ContactForm;

public interface ContactFormService {
	public ContactForm saveContactFormService(ContactForm contactForm);
	public List<ContactForm> readAllContactService();
	public void deleteContactService(int id);
}
