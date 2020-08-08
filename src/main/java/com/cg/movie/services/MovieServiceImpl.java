package com.cg.movie.services;

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
	public List<Movie> findAllMovie() {
		return movieRepo.findAll();
	}

	@Override
	public Movie update(Movie movie, int movieId) {
		return null;
	}

	@Override
	public void deleteMovie(Movie movie) {
		movieRepo.delete(movie);
	}

}
