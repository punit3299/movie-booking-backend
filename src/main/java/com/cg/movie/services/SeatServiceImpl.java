package com.cg.movie.services;

import java.sql.Timestamp;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.BookingRepository;
import com.cg.movie.dao.CityRepository;
import com.cg.movie.dao.CustomerRepository;
import com.cg.movie.dao.MovieRepository;
import com.cg.movie.dao.ScreenRepository;
import com.cg.movie.dao.SeatRepository;
import com.cg.movie.dao.ShowRepository;
import com.cg.movie.dao.TheatreRepository;
import com.cg.movie.dao.TicketRepository;
import com.cg.movie.dao.TransactionRepository;
import com.cg.movie.entities.Booking;
import com.cg.movie.entities.Customer;
import com.cg.movie.entities.Movie;
import com.cg.movie.entities.Screen;
import com.cg.movie.entities.Seat;
import com.cg.movie.entities.Show;
import com.cg.movie.entities.Theatre;
import com.cg.movie.entities.Ticket;
import com.cg.movie.entities.Transaction;
import com.cg.movie.exception.SeatAlreadyBookedException;
import com.cg.movie.response.BookTicketDetails;
import com.cg.movie.response.BookedDetailsOfTicket;
@Service
public class SeatServiceImpl implements ISeatService {

	@Autowired
	SeatRepository seatRepo;
	@Autowired
	CityRepository cityRepo;
	@Autowired
	ScreenRepository screenRepo;
	@Autowired
	MovieRepository movieRepo;
	@Autowired
	ShowRepository showRepo;
	@Autowired
	TheatreRepository theatreRepo;
	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	BookingRepository bookingRepo;
	@Autowired
	TicketRepository ticketRepo;
	
	@Autowired
	TransactionRepository transactionRepo;
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Override
	public BookedDetailsOfTicket bookSeat(BookTicketDetails details) {
		
		Screen screen=screenRepo.findById(details.getScreenId()).get();
		
		Customer customer=customerRepo.findById(details.getCustomerId()).get();
		
		Movie movie=movieRepo.findById(details.getMovieId()).get();
		
		Show show= showRepo.findById(details.getShowId()).get();
		
		Theatre theatre=theatreRepo.findById(details.getTheatreId()).get();
		
		Ticket ticket= new Ticket();
		
		ticket.setScreenName(screen.getScreenName());
		ticket.setSeatName(details.getSeatNo());
		ticket.setTicketStatus(true);
		ticket.setCustomer(customer);
		Ticket bookedTicket=ticketRepo.save(ticket);
		customer.addTicket(bookedTicket);
		
		Transaction transaction=new Transaction();
		transaction.setShow(show);
		transaction.setTransactionMessage("Movie "+ movie.getMovieName()+" booked, Price Rs. "+details.getTicketPrice());
		transaction.setTransactionTime(Timestamp.from(Instant.now()));
		Transaction bookedTransaction=transactionRepo.save(transaction);
		
		Booking booking= new Booking();
		
		booking.setBookingDate(details.getBookingDate());
		booking.setMovie(movie.getMovieName());
		booking.setStatus(true);
		booking.setTotalCost(details.getTicketPrice());
		booking.setShow(show);
		booking.setTicket(bookedTicket);
		booking.setTransaction(bookedTransaction);
		bookingRepo.save(booking);
		
		if(seatRepo.findSeatByShowId(show.getShowId())==null) {
			Seat seat=new Seat();
			seat.setSeatNumber(details.getSeatNo());
			seat.setSeatPrice(details.getTicketPrice());
			seat.setShow(show);
			seatRepo.save(seat);
			show.addSeat(seat);
		}
		else {
			Seat seat=seatRepo.findSeatByShowId(show.getShowId());
			if(seat.getSeatNumber().contains(details.getSeatNo())) {
				logger.error("Seats already booked");
				throw new SeatAlreadyBookedException("Seats Already Booked");
			}
			else {
				seat.setSeatNumber(seat.getSeatNumber()+details.getSeatNo());
				seatRepo.save(seat);
			}
			
		}
		
		BookedDetailsOfTicket bookedDeatilsOfTicket=new BookedDetailsOfTicket();
		bookedDeatilsOfTicket.setBookingId(booking.getBookingId());
		bookedDeatilsOfTicket.setBookingDate(details.getBookingDate());
		bookedDeatilsOfTicket.setCityName(details.getCityName());
		bookedDeatilsOfTicket.setMovieName(movie.getMovieName());
		bookedDeatilsOfTicket.setScreenName(screen.getScreenName());
		bookedDeatilsOfTicket.setSeatNo(details.getSeatNo());
		bookedDeatilsOfTicket.setShowDate(details.getShowDate());
		bookedDeatilsOfTicket.setTheatreName(theatre.getTheatreName());
		bookedDeatilsOfTicket.setTotalCost(details.getTicketPrice());
		
		return bookedDeatilsOfTicket;
		
		
	}

}
