package com.cg.movie.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.MovieRepository;
import com.cg.movie.entities.Movie;

@Service
public class MovieServiceImpl implements IMovieService {

	@Autowired
	MovieRepository movieRepo;
	

	@Override
	public Movie addMovie(Movie movie) {
		Movie movie1=movieRepo.save(movie);
		return movie1;	
	}

	@Override
	public Set<Movie> findAllMovie() {
		List<Movie> movieList=movieRepo.findAll();
		Set<Movie> movieList1=new HashSet<>(movieList);
		return movieList1;
	}


	@Override
	public boolean deleteMovieById(long movieId) {
		if(movieRepo.existsById(movieId))
		{
			movieRepo.deleteMovieById(true,movieId);
			return true;
		}
		return false;
	}

}
