package com.cg.movie.services;

import java.util.List;

import com.cg.movie.entities.Booking;

public interface IBookingService {
    /*
     * This method is to add new booking which is done by customer.
     * This takes booking id as the primary key, booking time, showId , transactionId and 
     * movieId as foreign keys and returns the details to customer
     */
	public Booking addBooking(Booking booking);
	
	/*
	 * This method is to get a booking by using booking is for the customer. It returns 
	 * booking time, showId, transactionId and movieId for customer reference
	 */
	public Booking getBooking(Long id);
	
	/*
	 * This method id get all bookings done by a customer. It returns a list of 
	 * bookings	
	 */
	public List<Booking> getPreviousBookings(Long id);
	
}
