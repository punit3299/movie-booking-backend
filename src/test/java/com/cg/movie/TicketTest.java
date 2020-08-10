package com.cg.movie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.movie.dao.TicketRepository;
import com.cg.movie.entities.Ticket;
import com.cg.movie.services.ITicketService;

@SpringBootTest
class TicketTest {

	@Autowired
	ITicketService iTicketService;

	@MockBean
	TicketRepository ticketRepo;

	@Test
	public void bookTicketTest() {
		Ticket ticket = new Ticket(new Long(1),  "First", true, "S1");
		when(ticketRepo.save(ticket)).thenReturn(ticket);
		assertEquals(ticket, iTicketService.bookTicket(ticket));
	}
	
	@Test
	public void cancelTicketTest() {
		Ticket ticket = new Ticket(new Long(1), "First", true, "S1");
		iTicketService.cancelTicket(ticket);
		verify(ticketRepo,times(1)).delete(ticket);
	}

}
