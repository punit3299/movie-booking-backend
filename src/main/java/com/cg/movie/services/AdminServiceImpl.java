package com.cg.movie.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

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
import com.cg.movie.exception.BookingNotFoundException;
import com.cg.movie.exception.CustomerNotFoundException;
import com.cg.movie.exception.GenrewiseMovieNotFoundException;
import com.cg.movie.exception.MoviesNotFoundException;
import com.cg.movie.exception.RevenueNotFoundException;
import com.cg.movie.exception.TheatresNotFoundException;
import com.cg.movie.response.GenderResponse;
import com.cg.movie.response.GenreResponse;

@Service
@Transactional
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

	/**
	 * Getting count of customers
	 */

	@Override
	public Long countOfCustomers() {

		List<Customer> customersList = customerRepo.findAll();

		if (customersList != null) {
			logger.info(customersList.size() + " Customers returned successfully");
			return new Long(customersList.size());
		} else {
			logger.error("Customers Not Found");
			throw new CustomerNotFoundException("Customers Not Found");
		}
	}

	/**
	 * Getting count of theatres
	 */

	@Override
	public Long countOfTheatres() {

		List<Theatre> theatresList = theatreRepo.findAll();

		if (theatresList != null) {
			logger.info(theatresList.size() + " Theatres returned successfully");
			return new Long(theatresList.size());
		} else {
			logger.error("Theatres Not Found");
			throw new TheatresNotFoundException("Theatres Not Found");
		}
	}

	/**
	 * Getting count of movies
	 */

	@Override
	public Long countOfMovies() {

		List<Movie> moviesList = movieRepo.findAll();

		if (moviesList != null) {
			logger.info(moviesList.size() + " Movies returned successfully");
			return new Long(moviesList.size());
		} else {
			logger.error("Movies Not Found");
			throw new MoviesNotFoundException("Movies Not Found");
		}
	}

	/**
	 * Getting Top 3 Theatres
	 */

	@Override
	public List<Theatre> topThreeTheatres() {

		List<Theatre> theatresList = theatreRepo.topThreeTheatres().stream().limit(3).collect(Collectors.toList());

		if (theatresList != null) {
			logger.info("Top " + theatresList.size() + " Theatres returned successfully");
			return theatresList;
		} else {
			logger.error("Theatres Not Found");
			throw new TheatresNotFoundException("Theatres Not Found");
		}
	}

	/**
	 * Getting Top 3 Movies
	 */

	@Override
	public List<Movie> topThreeMovies() {

		List<Movie> moviesList = movieRepo.topThreeMovies().stream().limit(3).collect(Collectors.toList());

		if (moviesList != null) {
			logger.info("Top " + moviesList.size() + " Movies returned successfully");
			return moviesList;
		} else {
			logger.error("Movies Not Found");
			throw new MoviesNotFoundException("Movies Not Found");
		}
	}

	/**
	 * Getting Today's Revenue
	 */

	@Override
	public Double todayRevenue() {

		Double todayRevenue = bookingRepo.todayRevenue();

		System.out.println(todayRevenue);
		if (todayRevenue != null && todayRevenue >= 0.0) {
			logger.info("Today's revenue is " + todayRevenue);
			return todayRevenue;
		} else if (todayRevenue == null) {
			todayRevenue = new Double(0);
			logger.info("Today's revenue is " + todayRevenue);
			return todayRevenue;
		} else {
			logger.error("No Revenue Today");
			throw new RevenueNotFoundException("No Revenue Today");
		}

	}

	/**
	 * Getting Today's Booking Count
	 */

	@Override
	public Integer todayBookingCount() {

		Integer todayBookingCount = bookingRepo.todayBookingCount();

		if (todayBookingCount != null && todayBookingCount >= 0) {
			logger.info(todayBookingCount + " Bookings Today");
			return todayBookingCount;
		} else {
			logger.error("No Bookings Today");
			throw new BookingNotFoundException("No Bookings Today");
		}
	}

	/**
	 * Getting gender-wise count of customers
	 */

	@Override
	public GenderResponse genderwiseCount() {

		List<Customer> customers = customerRepo.findAll();
		if (customers != null) {
			Long male = customers.stream().filter(e -> e.getCustomerGender().equals("Male")).count();
			Long female = customers.stream().filter(e -> e.getCustomerGender().equals("Female")).count();
			Long others = customers.stream().filter(e -> e.getCustomerGender().equals("Others")).count();
			logger.info(male + " Males, " + female + " Females, " + others + " Others returned successfully");
			return new GenderResponse(male, female, others);
		} else {
			logger.error("Customers Not Found");
			throw new CustomerNotFoundException("Customers Not Found");
		}

	}

	/**
	 * Getting genre-wise movies count
	 */

	@Override
	public List<GenreResponse> genrewiseMoviesCount() {
		List<GenreResponse> list = movieRepo.genrewiseMoviesCount();
		if(list!=null) {
			logger.info("List returned successfully");
			return list;
		}
		else {
			logger.error("No Genrewise Movies Found");
			throw new GenrewiseMovieNotFoundException("No Genrewise Movies Found");
		}
	}

	@Override
	public List<Double> recentRevenues() {
		return bookingRepo.recentRevenues();
	}

	@Override
	public List<Double> recentBookingsCount() {
		return bookingRepo.recentBookingsCount();
	}
	
	

}
