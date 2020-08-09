package com.cg.movie.services;

import com.cg.movie.entities.Booking;

public interface IBookingService {

	public Booking addBooking(Booking booking);
	
	public Booking getBooking(Long id);
	
}
