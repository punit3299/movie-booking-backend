package com.cg.movie.services;

import java.util.Set;

import com.cg.movie.entities.Movie;

public interface IMovieService {
	
	public Movie addMovie(Movie movie);

	Set<Movie> findAllMovie();

	boolean deleteMovieById(long movieId);

}
