package com.cg.movie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.movie.dao.BookingRepository;
import com.cg.movie.dao.CustomerRepository;
import com.cg.movie.dao.MovieRepository;
import com.cg.movie.dao.TheatreRepository;
import com.cg.movie.entities.Booking;
import com.cg.movie.entities.Customer;
import com.cg.movie.entities.Movie;
import com.cg.movie.entities.Theatre;
import com.cg.movie.exception.BookingNotFoundException;
import com.cg.movie.exception.CustomerNotFoundException;
import com.cg.movie.exception.MoviesNotFoundException;
import com.cg.movie.exception.RevenueNotFoundException;
import com.cg.movie.exception.TheatresNotFoundException;
import com.cg.movie.services.IAdminService;

@SpringBootTest
public class AdminTest {

	@Autowired
	IAdminService adminService;

	@MockBean
	CustomerRepository customerRepo;

	@MockBean
	TheatreRepository theatreRepo;

	@MockBean
	MovieRepository movieRepo;

	@MockBean
	BookingRepository bookingRepo;
	
	private Logger logger = Logger.getLogger(getClass());

	/**
	 * Testing count of customers
	 */

	@Test
	public void countOfCustomersTest() {
		
		logger.info("Counting Customers Testing started");
		
		Customer customer1 = new Customer(new Long(1), "Raj", "raj123", 7977977977L, "Male", 0);
		Customer customer2 = new Customer(new Long(2), "Punit", "punit123", 9879787937L, "Male", 0);
		Customer customer3 = new Customer(new Long(3), "Mayank", "mayank123", 8794911937L, "Male", 0);
		when(customerRepo.findAll())
				.thenReturn(Stream.of(customer1, customer2, customer3).collect(Collectors.toList()));
		assertEquals(3, adminService.countOfCustomers());

		List<Customer> customers = new ArrayList<Customer>();
		when(customerRepo.findAll()).thenReturn(customers = null);
		assertThrows(CustomerNotFoundException.class, () -> {
			adminService.countOfCustomers();
		});
		
		logger.info("Counting Customers Testing ended");
	}

	/**
	 * Testing count of theatres
	 */

	@Test
	public void countOfTheatresTest() {
		
		logger.info("Counting Theatres Testing started");
		
		Theatre theatre1 = new Theatre(new Long(1), "Xion", 3, "Raj", 7977977977L);
		Theatre theatre2 = new Theatre(new Long(2), "PVR", 5, "Punit", 8767897977L);
		when(theatreRepo.findAll()).thenReturn(Stream.of(theatre1, theatre2).collect(Collectors.toList()));
		assertEquals(2, adminService.countOfTheatres());

		List<Theatre> theatres = new ArrayList<Theatre>();
		when(theatreRepo.findAll()).thenReturn(theatres = null);
		assertThrows(TheatresNotFoundException.class, () -> {
			adminService.countOfTheatres();
		});
		
		logger.info("Counting Customers Testing ended");
	}

	/**
	 * Testing count of movies
	 */

	@Test
	public void countOfMoviesTest() {
		
		logger.info("Counting Movies Testing started");
		
		Movie movie1 = new Movie(new Long(1), "3 Idiots", "Comedy", "Rajkumar Hirani", new Double(150), 5,
				new Timestamp(System.currentTimeMillis()));
		when(movieRepo.findAll()).thenReturn(Stream.of(movie1).collect(Collectors.toList()));
		assertEquals(1, adminService.countOfMovies());

		List<Movie> movies = new ArrayList<Movie>();
		when(movieRepo.findAll()).thenReturn(movies = null);
		assertThrows(MoviesNotFoundException.class, () -> {
			adminService.countOfMovies();
		});
		
		logger.info("Counting Movies Testing ended");
	}

	/**
	 * Testing today Revenue
	 */

	@Test
	public void todayRevenueTest() {
		
		logger.info("Calaculating today's revenue Testing started");
		
		String time = "2018-09-01 09:01:15";
		Booking booking1 = new Booking(new Long(1), Timestamp.valueOf(time), 100.1);
		Booking booking2 = new Booking(new Long(2), Timestamp.valueOf(time), 200.5);
		Double todayRevenue = booking1.getTotalCost() + booking2.getTotalCost();
		when(bookingRepo.todayRevenue()).thenReturn(todayRevenue);
		assertEquals(todayRevenue, adminService.todayRevenue());

		when(bookingRepo.todayRevenue()).thenReturn(todayRevenue=0.0);
		assertThrows(RevenueNotFoundException.class, ()->{adminService.todayRevenue();});
		
		logger.info("Calaculating today's revenue Testing ended");
	}
	
	/**
	 * Testing today booking count
	 */

	@Test
	public void todayBookingCountTest() {
		
		logger.info("Calaculating today's booking count Testing started");
		
		String time = "2018-09-01 09:01:15";
		Booking booking1 = new Booking(new Long(1), Timestamp.valueOf(time), 100.1);
		Booking booking2 = new Booking(new Long(2), Timestamp.valueOf(time), 200.5);
		when(bookingRepo.todayBookingCount()).thenReturn(2);
		assertEquals(2, adminService.todayBookingCount());

		when(bookingRepo.todayBookingCount()).thenReturn(-1);
		assertThrows(BookingNotFoundException.class, ()->{adminService.todayBookingCount();});
		
		logger.info("Calaculating today's booking count Testing ended");
	}

}
