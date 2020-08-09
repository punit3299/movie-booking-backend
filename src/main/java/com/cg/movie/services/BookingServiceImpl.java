package com.cg.movie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.BookingRepository;
import com.cg.movie.entities.Booking;

@Service
public class BookingServiceImpl implements IBookingService {

	@Autowired
	BookingRepository bookingRepo;

	@Override
	public Booking addBooking(Booking booking) {
		// TODO Auto-generated method stub
		return bookingRepo.save(booking);
	}
	
	@Override 
	public Booking getBooking(Long id)
	{
		return  bookingRepo.getOne(id);
	}
	
    
}
