package com.cg.movie.services;

import java.util.List;

import com.cg.movie.entities.Movie;
import com.cg.movie.entities.Theatre;
import com.cg.movie.exception.CustomersNotFoundException;
import com.cg.movie.response.GenderResponse;

public interface IAdminService {
	
	public Long countOfCustomers() throws CustomersNotFoundException;

	public Long countOfTheatres();

	public Long countOfMovies();

	public List<Theatre> topThreeTheatres();

	public List<Movie> topThreeMovies();

	public Double todayRevenue();

	public Integer todayBookingCount();

	public GenderResponse genderwiseCount();

}
