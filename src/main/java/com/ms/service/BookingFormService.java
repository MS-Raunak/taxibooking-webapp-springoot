package com.ms.service;

import java.util.List;

import com.ms.model.BookingForm;


public interface BookingFormService {
	public BookingForm saveBookingFormService(BookingForm bookingForm);
	public List<BookingForm> readAllBookingService();
	public void deleteBookingService(int id);
}
