package com.cg.movie.services;

import com.cg.movie.entities.Ticket;

public interface ITicketService {
	
	public Ticket bookTicket(Ticket ticket);
	
	public void cancelTicket(Ticket ticket);

}
