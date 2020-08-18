package com.cg.movie.services;

import com.cg.movie.entities.Ticket;

public interface ITicketService {

	Ticket bookTicket(Ticket ticket);

	Ticket findTicketById(long ticketId);

	Ticket cancelTicket(long customerId,Ticket ticket);

}
