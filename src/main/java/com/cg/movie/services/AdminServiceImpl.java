package com.cg.movie.services;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.BookingRepository;
import com.cg.movie.dao.CustomerRepository;
import com.cg.movie.dao.MovieRepository;
import com.cg.movie.dao.TheatreRepository;
import com.cg.movie.entities.Customer;
import com.cg.movie.entities.Movie;
import com.cg.movie.entities.Theatre;
import com.cg.movie.exception.CustomerListNotFoundException;
import com.cg.movie.response.GenderResponse;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	CustomerRepository customerRepo;

	@Autowired
	TheatreRepository theatreRepo;

	@Autowired
	MovieRepository movieRepo;

	@Autowired
	BookingRepository bookingRepo;

	private Logger logger = Logger.getLogger(getClass());

	@Override
	public Long countOfCustomers() {
		
		List<Customer> customerList = customerRepo.findAll();

		if (customerList != null) {
			logger.info("Customer List returned Successfully");
			return new Long(customerList.size());
		} 
		else {
			logger.error("Customer List Not Found");
			throw new CustomerListNotFoundException("Customer List Not Found");
		}
		
	}

	@Override
	public Long countOfTheatres() {
		return new Long(theatreRepo.findAll().size());
	}

	@Override
	public Long countOfMovies() {
		return new Long(movieRepo.findAll().size());
	}

	@Override
	public List<Theatre> topThreeTheatres() {
		return theatreRepo.topThreeTheatres().stream().limit(3).collect(Collectors.toList());
	}

	@Override
	public List<Movie> topThreeMovies() {
		return movieRepo.topThreeMovies().stream().limit(3).collect(Collectors.toList());
	}

	@Override
	public Double todayRevenue() {
		return bookingRepo.todayRevenue();
	}

	@Override
	public Integer todayBookingCount() {
		return bookingRepo.todayBookingCount();
	}

	@Override
	public GenderResponse genderwiseCount() {
		List<Customer> customers = customerRepo.findAll();
		Long male = customers.stream().filter(e -> e.getCustomerGender().equals("Male")).count();
		Long female = customers.stream().filter(e -> e.getCustomerGender().equals("Female")).count();
		Long others = customers.stream().filter(e -> e.getCustomerGender().equals("Others")).count();
		return new GenderResponse(male, female, others);
	}

}
