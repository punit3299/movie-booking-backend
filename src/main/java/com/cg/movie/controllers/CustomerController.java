package com.cg.movie.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

import com.cg.movie.entities.Booking;
import com.cg.movie.entities.Customer;
import com.cg.movie.entities.Movie;
import com.cg.movie.entities.Show;
import com.cg.movie.entities.Ticket;
import com.cg.movie.entities.Transaction;
import com.cg.movie.exception.CustomerNotFoundException;
import com.cg.movie.exception.MoviesNotFoundException;
import com.cg.movie.exception.TicketNotFoundException;
import com.cg.movie.response.BookTicketDetails;
import com.cg.movie.response.BookedDetailsOfTicket;
import com.cg.movie.services.IBookingService;
import com.cg.movie.services.ICityService;
import com.cg.movie.services.ICustomerService;
import com.cg.movie.services.IMovieService;
import com.cg.movie.services.IScreenService;
import com.cg.movie.services.ISeatService;
import com.cg.movie.services.IShowService;
import com.cg.movie.services.ITheatreService;
import com.cg.movie.services.ITicketService;
import com.cg.movie.services.ITransactionService;

@RestController
@CrossOrigin("*")
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	ISeatService seatService;
	
	@Autowired
	IScreenService screenService;
	
	@Autowired
	IShowService showService;
	
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
	
	@Autowired
	IBookingService bookingService;
	
	@Autowired
	ITransactionService transactionService;
	
	
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
	 * Controller to Get Transactions
	 */
	
	@GetMapping(value="/getAllTransactions/{customerId}")
	public ResponseEntity<List<Transaction>> getAllTransactions(@PathVariable long customerId){
		
		logger.trace("at getAllTransactions methof");
		
		List<Transaction> transactions= transactionService.getCustomerTransactions(customerId);
		
		return new ResponseEntity<List<Transaction>>(transactions,HttpStatus.OK);
		
	}
	
	/*
	 * Controller to Cancel Ticket
	 */
	@PutMapping(value="/cancelTicket/{customerId}")
	public ResponseEntity<Ticket> cancelTicket(@PathVariable long customerId,@RequestBody Ticket ticket) throws TicketNotFoundException{
		
		logger.trace("at cancelTicket method");
		
		ticket=ticketService.cancelTicket(customerId, ticket);
		return new ResponseEntity<Ticket>(ticket,HttpStatus.OK);
		
	}
	/*
	 * Controller to book seat
	 */
	
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
	
	/*
	 * Controller to View only all the cities to user
	 */
	@GetMapping(value = "/city/list")
	public ResponseEntity<List<String>> getAllCities() {
		List<String> allCity= new ArrayList<String>();
		cityService.viewAllCity().forEach(e -> {
			String cityName = e.getCityName();
			allCity.add(cityName);
		});
		
		return new ResponseEntity<List<String>>(allCity, HttpStatus.OK);
	}
	
	/*
	 * Controller to search the cities for the user
	 */
	@GetMapping(value = "/city/{search}")
	public ResponseEntity<List<String>> searchCity(@PathVariable String search){
		List<String> allCity= new ArrayList<String>();
		cityService.searchCity(search).forEach(e -> {
			String cityName = e.getCityName();
			allCity.add(cityName);
		});	
		
		return new ResponseEntity<List<String>>(allCity, HttpStatus.OK);
	}
	
	/*
	 * Controller to View only all the cities to user
	 */
	@GetMapping(value = "/theatre/list")
	public ResponseEntity<List<String>> getAllTheatre() {
		List<String> allTheatres= new ArrayList<String>();
		theatreService.viewAllTheatre().forEach(e -> {
			String theatreName = e.getTheatreName();
			allTheatres.add(theatreName);
		});
		
		return new ResponseEntity<List<String>>(allTheatres, HttpStatus.OK);
	}
	
	/*
	 * Controller to search the Theaters for the user
	 */
	@GetMapping(value = "/theatre/{search}")
	public ResponseEntity<List<String>> searchTheatre(@PathVariable String search){
		List<String> allTheatres= new ArrayList<String>();
		theatreService.searchTheater(search).forEach(e -> {
			String theatreName = e.getTheatreName();
			allTheatres.add(theatreName);
		});	
		return new ResponseEntity<List<String>>(allTheatres, HttpStatus.OK);
	}
	
	/*
	 * Controller to View only all the movies to user
	 */
	@GetMapping(value = "/movie/list")
	public ResponseEntity<List<String>> getAllMovie() {
		List<String> allMovie= new ArrayList<String>();
		movieService.findAllMovie().forEach(e -> {
			String movieName = e.getMovieName();
			allMovie.add(movieName);
		});
		
		return new ResponseEntity<List<String>>(allMovie, HttpStatus.OK);
	}
	
	/*
	 * Controller to search the movies for the user
	 */
	// .........I will Edit this again so please leave it...............
	@PostMapping(value = "/movie")
	public ResponseEntity<String> searchMovie(@RequestBody Movie movie) throws MoviesNotFoundException {
		
		String movieName=movieService.searchMovie(movie.getMovieName());
		return new ResponseEntity<String>(movieName, HttpStatus.OK);
	}
	
	/*
	 * Controller to fetch all previous bookings for the customer
	 */
     @GetMapping(value="/booking/all/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<List<Booking>> getPreviousBookings(@PathVariable long customerId)
     {
    	 List<Booking> bookings= bookingService.getPreviousBookings(new Long(customerId));
    	 return new ResponseEntity<List<Booking>> (bookings,HttpStatus.OK);
     }
     
     /*
      * Controller to fetch single booking with booking id
      */
     @GetMapping(value ="/booking/{bookingId}", produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<Booking> getBooking(@PathVariable long bookingId){
    	logger.trace("at getBooking method in controller");
    	 Booking booking=bookingService.getBooking(bookingId);
    	 return new ResponseEntity<Booking> (booking,HttpStatus.OK);
     }
     
     /*
      * Controller to fetch all shows
      */
     @GetMapping(value="/show/all")
     public ResponseEntity <List<Show>> getAllShows(){
    	 logger.trace("at getAllShows method ");
    	 List<Show> shows=showService.getAllShows();
    	 return new ResponseEntity<List<Show>> (shows,HttpStatus.OK);
     }
     
     /*
      * controller to get shows by theatre for user
      */
     @GetMapping(value ="/show/byTheatre/{theatreId}")
     public ResponseEntity<List<Show>> getShowByTheatreId(@PathVariable Long theatreId){
    	 logger.trace("at getAllShows method ");
    	 List<Show> shows=showService.getShowByTheatreId(theatreId);
   
    	 return new ResponseEntity<List<Show>>(shows,HttpStatus.OK);
     }
     
     /*
      * controller to get shows by movie for the user
      */
     @GetMapping(value ="/show/byMovie/{movieId}")
     public ResponseEntity<List<Show>> getShowByMovieId(@PathVariable Long movieId){
    	 logger.trace("at getShowByMovieId method ");
    	 List<Show> shows=showService.getShowByMovieId(movieId);
    	 return new ResponseEntity<List<Show>>(shows,HttpStatus.OK);
     }
     
     
}





