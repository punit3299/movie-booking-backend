package com.cg.movie.controllers;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.movie.entities.Customer;
import com.cg.movie.entities.Ticket;
import com.cg.movie.exception.CustomerNotFoundException;
import com.cg.movie.response.BookTicketDetails;
import com.cg.movie.response.BookedDetailsOfTicket;
import com.cg.movie.services.ICustomerService;
import com.cg.movie.services.ISeatService;

@RestController
@CrossOrigin("*")
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	ISeatService seatService;
	
	@Autowired
	ICustomerService customerService;
	
	private Logger logger = Logger.getLogger(getClass());
	
	/*
	 * Controller to Add Customer
	 */
	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
		
		logger.trace("at addCustomer method");
		
		Customer newCustomer=customerService.addCustomer(customer);
		return new ResponseEntity<Customer>(newCustomer,HttpStatus.CREATED);
		
	}
	
	/*
	 * Controller to Add Money
	 */
	
	@PutMapping(value="/addMoney/{customerId}/{amount}")
	public ResponseEntity<Customer> addMoney(@PathVariable long customerId, @PathVariable int amount)
		throws CustomerNotFoundException{
		
		logger.trace("at addMoney method");
		
		Customer customer = customerService.findCustomerById(customerId);
		customer= customerService.addMoneyToWallet(customer, amount);
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
		
	}
	
	/*
	 * Controller to Refund Money
	 */
	
	@PutMapping(value="/refundMoney/{customerId}/{amount}")
	public ResponseEntity<Customer> refundMoney(@PathVariable long customerId, @PathVariable int amount)
		throws CustomerNotFoundException{
		
		logger.trace("at refundMoney method");
		
		Customer customer = customerService.findCustomerById(customerId);
		customer= customerService.refundMoneyToWallet(customer, amount);
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
		
	}
	
	@PutMapping(value="/cancelTicket/{ticketId}")
	public ResponseEntity<Ticket> cancelTicket(@PathVariable long ticketId){
		return null;
		
	}
	
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
