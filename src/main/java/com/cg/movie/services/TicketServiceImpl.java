package com.cg.movie.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.BookingRepository;
import com.cg.movie.dao.SeatRepository;
import com.cg.movie.dao.TicketRepository;
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
	
	
	private Logger logger = Logger.getLogger(getClass());

	@Override
	public Ticket bookTicket(Ticket ticket) {
		return ticketRepo.save(ticket);
	}
	
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

	@Override
	public Ticket cancelTicket(Ticket ticket) {
		
		long showId=bookingRepo.getShowId(ticket.getTicketId());
		
		Seat seat = seatRepo.getSeat(showId);
		String seatNumber=seat.getSeatNumber();
		seatNumber=seatNumber.replace(ticket.getSeatName(),"");
		seat.setSeatNumber(seatNumber);
		ticket.setTicketStatus(false);
		return ticketRepo.save(ticket);
		
	}

	

	
	
	
	

}
