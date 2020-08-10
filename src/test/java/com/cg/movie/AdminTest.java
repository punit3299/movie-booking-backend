package com.cg.movie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.movie.dao.AdminRepository;
import com.cg.movie.dao.CustomerRepository;
import com.cg.movie.dao.MovieRepository;
import com.cg.movie.dao.TheatreRepository;
import com.cg.movie.entities.Customer;
import com.cg.movie.entities.Movie;
import com.cg.movie.entities.Theatre;
import com.cg.movie.services.AdminServiceImpl;
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
	
	//count of customers test 
	
	@Test
	public void countOfCustomersTest() {
		Customer c1= new Customer(new Long(1), "Raj", "raj123", 7977977977L, "Male", 0);
		Customer c2= new Customer(new Long(2), "Punit", "punit123", 9879787937L, "Male", 0);
		Customer c3= new Customer(new Long(3), "Mayank", "mayank123", 8794911937L, "Male", 0);
		when(customerRepo.findAll()).thenReturn(Stream.of(c1,c2,c3).collect(Collectors.toList()));
		assertEquals(3, adminService.countOfCustomers());
	}
	
	//count of theatres test
	
	@Test
	public void countOfTheatresTest() {
		Theatre t1 = new Theatre(new Long(1), "Xion", 3, "Raj", 7977977977L);
		Theatre t2 = new Theatre(new Long(2), "Xion1", 5, "Punit", 8767897977L);
		when(theatreRepo.findAll()).thenReturn(Stream.of(t1,t2).collect(Collectors.toList()));
		assertEquals(2, adminService.countOfTheatres());
	}
	
	//count of movies test
	
	@Test
	public void countOfMoviesTest() {
		Movie m1=new Movie(new Long(1), "3 Idiots", "Comedy", "Rajkumar Hirani", new Double(150), 5, new Timestamp(System.currentTimeMillis()));
		when(movieRepo.findAll()).thenReturn(Stream.of(m1).collect(Collectors.toList()));
		assertEquals(1, adminService.countOfMovies());
	}
	
}
