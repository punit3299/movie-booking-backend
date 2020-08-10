package com.cg.movie.controllers;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.movie.entities.Ticket;
import com.cg.movie.response.BookTicketDetails;
import com.cg.movie.response.BookedDetailsOfTicket;
import com.cg.movie.services.ISeatService;

@RestController
@CrossOrigin("*")
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	ISeatService seatService;
	
	@PostMapping(value = "/bookSeat", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookedDetailsOfTicket> bookSeat(@RequestBody BookTicketDetails bookTicketDetails) {
		
		BookedDetailsOfTicket bookedDetailsOfTicket= seatService.bookSeat(bookTicketDetails);
		
		return new ResponseEntity<BookedDetailsOfTicket>(bookedDetailsOfTicket,HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value="/hello")
	public Timestamp helloRaman()
	{
		Timestamp ts=new Timestamp(System.currentTimeMillis());
		return ts;
	}
}
