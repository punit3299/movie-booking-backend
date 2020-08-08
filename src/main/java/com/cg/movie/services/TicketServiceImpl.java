package com.cg.movie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.TicketRepository;
import com.cg.movie.entities.Ticket;

@Service
public class TicketServiceImpl implements ITicketService {

	@Autowired
	TicketRepository ticketRepo;

	@Override
	public Ticket bookTicket(Ticket ticket) {
		return ticketRepo.save(ticket);
	}

	@Override
	public void cancelTicket(Ticket ticket) {
		ticketRepo.delete(ticket);
	}

	
	
	
	

}
