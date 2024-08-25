package com.ms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.dao.ContactFormCrud;
import com.ms.model.ContactForm;

@Service
public class ContactFormServiceImpl implements ContactFormService{
	
	ContactFormCrud contactFormCrud;
	
	@Autowired   //setter method injection instead of filed injection
	public void setContactFormCrud(ContactFormCrud contactFormCrud) {
		this.contactFormCrud = contactFormCrud;
	}


	@Override
	public ContactForm saveContactFormService(ContactForm contactForm) {
		return contactFormCrud.save(contactForm);
		
	}


	@Override
	public List<ContactForm> readAllContactService() {
		return contactFormCrud.findAll();
	}


	@Override
	public void deleteContactService(int id) {
		contactFormCrud.deleteById(id);
	}

}
