package com.cg.movie.services;

import java.util.List;

import com.cg.movie.entities.Booking;
import com.cg.movie.entities.Movie;
import com.cg.movie.entities.Theatre;
import com.cg.movie.exception.CustomerNotFoundException;
import com.cg.movie.response.GenderResponse;
import com.cg.movie.response.GenreResponse;
import com.cg.movie.response.SuccessMessage;

public interface IAdminService {
	
	public Long countOfCustomers() throws CustomerNotFoundException;

	public Long countOfTheatres();

	public Long countOfMovies();

	public List<Theatre> topThreeTheatres();

	public List<Movie> topThreeMovies();

	public Double todayRevenue();

	public Integer todayBookingCount();

	public GenderResponse genderwiseCount();

	public List<GenreResponse> genrewiseMoviesCount();

	public List<Double> recentRevenues();

	public List<Integer> recentBookingsCount();

	public List<Booking> getBookings();

	public List<Booking> getRecentThreeBookings();

	public SuccessMessage deleteBookingById(Long bookingId);

	public boolean findEmailIfExists(String email);
}
