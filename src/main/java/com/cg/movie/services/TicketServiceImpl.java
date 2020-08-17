package com.cg.movie.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.BookingRepository;
import com.cg.movie.dao.CustomerRepository;
import com.cg.movie.dao.SeatRepository;
import com.cg.movie.dao.TicketRepository;
import com.cg.movie.entities.Booking;
import com.cg.movie.entities.Customer;
import com.cg.movie.entities.Seat;
import com.cg.movie.entities.Ticket;
import com.cg.movie.exception.TicketNotFoundException;

@Service
public class TicketServiceImpl implements ITicketService {

	@Autowired
	TicketRepository ticketRepo;
	
	@Autowired
	BookingRepository bookingRepo;
	
	@Autowired
	SeatRepository seatRepo;
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	ICustomerService customerService;
	
	
	private Logger logger = Logger.getLogger(getClass());

	@Override
	public Ticket bookTicket(Ticket ticket) {
		return ticketRepo.save(ticket);
	}
	
	/********************************************************************************
	 * 
	 * Method : findTicketById
	 * 
	 * Description: for finding Ticket by Id
	 * 
	 * @param  : ticketId		Ticket ticketId
	 * 
	 * @return : Ticket Entity
	 * 
	 *         Created by: Siddharth Raghuvanshi ,10 August 2020
	 * 
	 **********************************************************************************/
	@Override
	public Ticket findTicketById(long ticketId) {
		boolean checkTicket=ticketRepo.existsById(ticketId);
		
		if(checkTicket==false)
		{
			logger.error("Ticket not found with TicketId: "+ticketId);
			throw new TicketNotFoundException("Ticket Not Found");
		}
		else {
			
			logger.info(" Ticket found with TicketId: "+ticketId);
			Ticket ticket=ticketRepo.getOne(ticketId);
			return ticket;
		}
	}

	/********************************************************************************
	 * 
	 * Method : cancelTicket
	 * 
	 * Description: cancel ticket
	 * 
	 * @param  : ticket object
	 * 
	 * @return : Ticket Entity
	 * 
	 *         Created by: Siddharth Raghuvanshi ,10 August 2020
	 * 
	 **********************************************************************************/
	@Override
	public Ticket cancelTicket(long customerId,Ticket ticket) {
		
		
		Booking booking=bookingRepo.getBooking(ticket.getTicketId());
		
		long showId=bookingRepo.getShowId(ticket.getTicketId());
		
		Seat seat = seatRepo.getSeat(showId);
		
		String seatNumber=seat.getSeatNumber();
		seatNumber=seatNumber.replace(ticket.getSeatName(),"");
		seat.setSeatNumber(seatNumber);
		
		booking.setStatus(false);
		bookingRepo.save(booking);
		
		ticket.setTicketStatus(false);
		
		Customer customer=customerRepo.findById(customerId).get();
		customer=customerService.refundMoneyToWallet(customer, showId, booking.getTotalCost());
		
		return ticketRepo.save(ticket);
		
	}

	

	
	
	
	

}
