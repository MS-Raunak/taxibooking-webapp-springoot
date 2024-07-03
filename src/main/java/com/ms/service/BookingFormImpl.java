package com.ms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.dao.BookingFormCrud;
import com.ms.model.BookingForm;

@Service
public class BookingFormImpl implements BookingFormService {
	BookingFormCrud bookingFormCrud;
	@Autowired
	public void setBookingFormCrud(BookingFormCrud bookingFormCrud) {
		this.bookingFormCrud = bookingFormCrud;
	}
	
	@Override
	public BookingForm saveBookingFormService(BookingForm bookingForm) {
		BookingForm form = bookingFormCrud.save(bookingForm);
		return form;
	}

}
