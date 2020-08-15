package com.cg.movie.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.apache.log4j.Logger;
import com.cg.movie.dao.BookingRepository;
import com.cg.movie.entities.Booking;
import com.cg.movie.entities.Customer;
import com.cg.movie.exception.BookingNotFoundException;
import com.cg.movie.exception.CustomerNotFoundException;

@Service
public class BookingServiceImpl implements IBookingService {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private BookingRepository bookingRepo;

	@Override
	public Booking addBooking(Booking booking) {
		// TODO Auto-generated method stub
		return bookingRepo.save(booking);
	}
	
	@Override 
	public Booking getBooking(Long bookingId) 
	{
		
		if((bookingRepo.existsById(bookingId))==false)
		{
			logger.error("booking not found");
			throw new BookingNotFoundException("Booking with id "+bookingId+" doesnot exist");
		}
		else
		{
			logger.error("booking found sucessfully");
		return  bookingRepo.findById(bookingId).get();
	    }
	}
	
	
    @Override
    public List<Booking> getPreviousBookings(Long id){
    	List<Customer> customer=bookingRepo.findCustomerById(id);
    	if(customer==null || customer.isEmpty())
    	{
    		logger.error("booking not found");
			throw new CustomerNotFoundException("Customer with id "+id+" doesnot exist");
    	}
    	else
    	{
    	List<Booking> bookings=bookingRepo.findByCustomerId(id);
    	if(bookings.size()==0 || bookings.isEmpty())
    	{
    		logger.error("booking not found");
			throw new BookingNotFoundException("No Booking with Customer id "+id+" doesnot exist");
    	}
    	else
    	{
    		logger.info("Booking history found successfully");
    	return bookings ;
    	}}
    }
 
 
}

