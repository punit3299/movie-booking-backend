package com.cg.movie.services;

import java.util.List;
import java.util.Optional;

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
import com.cg.movie.entities.Booking;
import com.cg.movie.entities.City;
import com.cg.movie.entities.Customer;
import com.cg.movie.entities.Movie;
import com.cg.movie.entities.Screen;
import com.cg.movie.entities.Seat;
import com.cg.movie.entities.Show;
import com.cg.movie.entities.Theatre;
import com.cg.movie.entities.Ticket;
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
	
	@Override
	public BookedDetailsOfTicket bookSeat(BookTicketDetails details) {
		City city= cityRepo.findById(details.getCityId()).get();
		Theatre theatre=theatreRepo.findById(details.getTheatreId()).get();
		Screen screen=screenRepo.findById(details.getScreenId()).get();
		Show show=showRepo.findById(details.getShowId()).get();
		Movie movie=movieRepo.findById(details.getMovieId()).get();
		Customer customer=customerRepo.findById(details.getCustomerId()).get();
		
		Ticket ticket=new Ticket();
		ticket.setSeatName(details.getSeatNo());
	    ticket.setScreenName(screen.getScreenName());
	    ticket.setTicketStatus(true);
	    ticketRepo.save(ticket);
	    
	    customer.addTicket(ticket);
		
	    Booking booking=new Booking();
		booking.setBookingDate(details.getBookingDate());
		booking.setTotalCost(details.getTicketPrice());
		booking.setMovie(movie.getMovieName());
		booking.setStatus(true);
		booking.setShow(show);
		booking.setTicket(ticket);
		bookingRepo.save(booking);
	    
		int showAlreadyInSeatEntity=seatRepo.ifExistSeatOfShowId(details.getShowId());
		
		if(showAlreadyInSeatEntity!=0)
		{
			Seat seat=seatRepo.findSeatByShowId(details.getShowId());
			String seatNumber=seat.getSeatNumber()+"|"+details.getSeatNo();
			seat.setSeatNumber(seatNumber);
			seatRepo.save(seat);
		}
		else if(showAlreadyInSeatEntity==0)
		{
			Seat seat=new Seat();
			seat.setSeatNumber(details.getSeatNo());
			show.addSeat(seat);
			seatRepo.save(seat);
		}
		
	    BookedDetailsOfTicket bookedDetailsOfTicket=new BookedDetailsOfTicket();
	    bookedDetailsOfTicket.setBookingDate(details.getBookingDate());
	    bookedDetailsOfTicket.setCityName(city.getCityName());
	    bookedDetailsOfTicket.setMovieName(movie.getMovieName());
	    bookedDetailsOfTicket.setScreenName(screen.getScreenName());
	    bookedDetailsOfTicket.setSeatNo(details.getSeatNo());
	    bookedDetailsOfTicket.setShowDate(details.getShowDate());
	    bookedDetailsOfTicket.setTheatreName(theatre.getTheatreName());
	    bookedDetailsOfTicket.setTotalCost(details.getTicketPrice());
	    bookedDetailsOfTicket.setBookingId(booking.getBookingId());
	    
		return bookedDetailsOfTicket;
		
	}

}
