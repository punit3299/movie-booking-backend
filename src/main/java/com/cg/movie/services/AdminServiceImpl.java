package com.cg.movie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.CustomerRepository;
import com.cg.movie.dao.MovieRepository;
import com.cg.movie.dao.TheatreRepository;

@Service
public class AdminServiceImpl implements IAdminService{

	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	TheatreRepository theatreRepo;
	
	@Autowired
	MovieRepository movieRepo;

	@Override
	public Long countOfCustomers() {
		return new Long(customerRepo.findAll().size());
	}

	@Override
	public Long countOfTheatres() {
		return new Long(theatreRepo.findAll().size());
	}

	@Override
	public Long countOfMovies() {
		return new Long(movieRepo.findAll().size());
	}

}
