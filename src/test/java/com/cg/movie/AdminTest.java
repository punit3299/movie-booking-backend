package com.cg.movie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.movie.dao.CustomerRepository;
import com.cg.movie.dao.MovieRepository;
import com.cg.movie.dao.TheatreRepository;
import com.cg.movie.entities.Customer;
import com.cg.movie.entities.Movie;
import com.cg.movie.entities.Theatre;
import com.cg.movie.exception.CustomerNotFoundException;
import com.cg.movie.exception.MoviesNotFoundException;
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
	
	// Testing count of customers
	
	@Test
	public void countOfCustomersTest() {
		Customer customer1= new Customer(new Long(1), "Raj", "raj123", 7977977977L, "Male", 0);
		Customer customer2= new Customer(new Long(2), "Punit", "punit123", 9879787937L, "Male", 0);
		Customer customer3= new Customer(new Long(3), "Mayank", "mayank123", 8794911937L, "Male", 0);
		when(customerRepo.findAll()).thenReturn(Stream.of(customer1,customer2,customer3).collect(Collectors.toList()));
		assertEquals(3, adminService.countOfCustomers());
		
		List<Customer> customers = new ArrayList<Customer>();
		when(customerRepo.findAll()).thenReturn(customers=null);
		assertThrows(CustomerNotFoundException.class, ()->{adminService.countOfCustomers();});
	}
	
	//Testing count of theatres
	
	@Test
	public void countOfTheatresTest() {
		Theatre theatre1 = new Theatre(new Long(1), "Xion", 3, "Raj", 7977977977L);
		Theatre theatre2 = new Theatre(new Long(2), "PVR", 5, "Punit", 8767897977L);
		when(theatreRepo.findAll()).thenReturn(Stream.of(theatre1,theatre2).collect(Collectors.toList()));
		assertEquals(2, adminService.countOfTheatres());
		
		List<Theatre> theatres = new ArrayList<Theatre>();
		when(theatreRepo.findAll()).thenReturn(theatres=null);
		assertThrows(TheatresNotFoundException.class, ()->{adminService.countOfTheatres();});
	}
	
	//Testing count of movies
	
	@Disabled
	@Test
	public void countOfMoviesTest() {
		Movie movie1=new Movie(new Long(1), "3 Idiots", "Comedy", "Rajkumar Hirani", new Double(150), 5,new Timestamp(System.currentTimeMillis()),true);
		when(movieRepo.findAll()).thenReturn(Stream.of(movie1).collect(Collectors.toList()));
		assertEquals(1, adminService.countOfMovies());
		
		List<Movie> movies = new ArrayList<Movie>();
		when(movieRepo.findAll()).thenReturn(movies=null);
		assertThrows(MoviesNotFoundException.class, ()->{adminService.countOfMovies();});
	}
	
}
