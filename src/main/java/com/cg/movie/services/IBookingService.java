package com.cg.movie.services;

import java.util.List;

import com.cg.movie.entities.Booking;

public interface IBookingService {

	public Booking addBooking(Booking booking);
	
	public Booking getBooking(Long id);
	
	public Boolean verifyCustomerId(Long id);
	
	 public List<Booking> getPreviousBookings(Long id);
	
}
