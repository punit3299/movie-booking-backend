package com.cg.movie.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
import com.cg.movie.exception.TicketNotFoundException;
import com.cg.movie.response.BookTicketDetails;
import com.cg.movie.response.BookedDetailsOfTicket;
import com.cg.movie.services.ICityService;
import com.cg.movie.services.ICustomerService;
import com.cg.movie.services.IMovieService;
import com.cg.movie.services.ISeatService;
import com.cg.movie.services.ITheatreService;
import com.cg.movie.services.ITicketService;

@RestController
@CrossOrigin("*")
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	ISeatService seatService;
	
	@Autowired
	ICustomerService customerService;
	
	@Autowired
	ITicketService ticketService;
	
	@Autowired
	ICityService cityService;
	
	@Autowired
	ITheatreService theatreService;
	
	@Autowired
	IMovieService movieService;
	
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
	
	/*
	 * Controller to Cancel Ticket
	 */
	@PutMapping(value="/cancelTicket/{ticketId}")
	public ResponseEntity<Ticket> cancelTicket(@PathVariable long ticketId) throws TicketNotFoundException{
		
		logger.trace("at cancelTicket method");
		
		Ticket ticket=ticketService.findTicketById(ticketId);
		ticket=ticketService.cancelTicket(ticket);
		return new ResponseEntity<Ticket>(ticket,HttpStatus.OK);
		
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
	
	@GetMapping(value = "/city/list")
	public ResponseEntity<List<String>> getAllCities() {
		List<String> allCity= new ArrayList<String>();
		cityService.viewAllCity().forEach(e -> {
			String cityName = e.getCityName();
			allCity.add(cityName);
		});
		
		return new ResponseEntity<List<String>>(allCity, HttpStatus.OK);
	}
	
	@GetMapping(value = "/city/{search}")
	public ResponseEntity<List<String>> searchCity(@PathVariable String search){
		List<String> allCity= new ArrayList<String>();
		cityService.searchCity(search).forEach(e -> {
			String cityName = e.getCityName();
			allCity.add(cityName);
		});	
		return new ResponseEntity<List<String>>(allCity, HttpStatus.OK);
	}
}
