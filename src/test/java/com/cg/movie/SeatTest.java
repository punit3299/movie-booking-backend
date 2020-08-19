package com.cg.movie;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
import com.cg.movie.entities.City;
import com.cg.movie.entities.Customer;
import com.cg.movie.entities.Movie;
import com.cg.movie.entities.Screen;

import com.cg.movie.entities.Seat;

import com.cg.movie.entities.Show;
import com.cg.movie.entities.Theatre;
import com.cg.movie.entities.Ticket;
import com.cg.movie.entities.Transaction;
import com.cg.movie.exception.CustomerNotFoundException;
import com.cg.movie.exception.MovieDoesntExistException;
import com.cg.movie.exception.ScreenNotFoundException;
import com.cg.movie.exception.ShowDoesntExistException;

import com.cg.movie.exception.TheatreNotFoundException;

import com.cg.movie.response.BookTicketDetails;
import com.cg.movie.response.BookedDetailsOfTicket;
import com.cg.movie.services.ITheatreService;
import com.cg.movie.services.SeatServiceImpl;
@SpringBootTest
class SeatTest {
	@Autowired
	SeatServiceImpl seatService;
	@Autowired
	ITheatreService theatreService;
	
	@MockBean
	SeatRepository seatRepo;
	@MockBean
	ShowRepository showRepo;
	@MockBean
	ScreenRepository screenRepo;
	@MockBean
	MovieRepository movieRepo;
	@MockBean
	TheatreRepository theatreRepo;
	@MockBean
	CityRepository cityRepo;
	@MockBean
	CustomerRepository customerRepo;
	@MockBean
	TicketRepository ticketRepo;
	@MockBean
	BookingRepository bookingRepo;
	@MockBean
	TransactionRepository transactionRepo;
	private static City city;
	private static Theatre theatre;
	private static Screen screen;
	private static Show show;
	private static Movie movie;
	private static Customer customer;
	private static BookTicketDetails bookTicketDetails;
	private static BookedDetailsOfTicket bookedDetailsOfTicket;
	private static Booking booking;
	private static Ticket ticket;
	private static Transaction transaction;
	
	@BeforeEach
	public void init() {
		city =new City((long)101,"Pune");
		theatre = new Theatre((long) 10, "Xion", 5, "Mohit", 7973657728L);
		screen =  new Screen((long) 101, "Audi1", 75, true);

		show=new Show((long)1001,new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),"First show","Hindi");
		movie=new Movie((long)1111,"Fast and Furious","Action","Robert",120.5,9,new Timestamp(System.currentTimeMillis()),true);

		show=new Show((long)1001,new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),"First show", null);
		

		customer =new Customer((long)200,"Rohit","rohitpass",9898982221L,"Male",4500);
		booking=new Booking((long)50,new Timestamp(System.currentTimeMillis()),210.60);
		bookTicketDetails=new BookTicketDetails("25",(long)1001,(long)1111,(long)101,(long)10,(long)200,"Pune",210.60,new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()));
		bookedDetailsOfTicket=new BookedDetailsOfTicket(booking.getBookingId(),"Pune",theatre.getTheatreName(),screen.getScreenName(),"25",movie.getMovieName(),new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),210.60);

	    ticket=new Ticket((long)500,"25",true,screen.getScreenName());

		ticket=new Ticket((long)500,"25",true,screen.getScreenName());

	    transaction=new Transaction((long)555,"transaction done",new Timestamp(System.currentTimeMillis()));
	}
	 
    
    
    @Test
    public void checkCustomerTest()
    {
    	when(customerRepo.existsById(Mockito.anyLong())).thenReturn(false);
    	assertThrows(CustomerNotFoundException.class,()->{seatService.bookSeat(bookTicketDetails);});
    }
    @Test
    public void checkScreenTest()
    {   when(customerRepo.existsById(Mockito.anyLong())).thenReturn(true);
    	when(screenRepo.existsById(Mockito.anyLong())).thenReturn(false);
    	assertThrows(ScreenNotFoundException.class,()->{seatService.bookSeat(bookTicketDetails);});
    }
    @Test
    public void checkShowTest()
    {   when(customerRepo.existsById(Mockito.anyLong())).thenReturn(true);
    	when(screenRepo.existsById(Mockito.anyLong())).thenReturn(true);
    	when(showRepo.existsById(Mockito.anyLong())).thenReturn(false);
    	assertThrows(ShowDoesntExistException.class,()->{seatService.bookSeat(bookTicketDetails);});
    }
    @Test
    public void checkMovieTest()
    {   when(customerRepo.existsById(Mockito.anyLong())).thenReturn(true);
    	when(screenRepo.existsById(Mockito.anyLong())).thenReturn(true);
    	when(showRepo.existsById(Mockito.anyLong())).thenReturn(true);
    	when(movieRepo.existsById(Mockito.anyLong())).thenReturn(false);
    	assertThrows(MovieDoesntExistException.class,()->{seatService.bookSeat(bookTicketDetails);});
    }

}
