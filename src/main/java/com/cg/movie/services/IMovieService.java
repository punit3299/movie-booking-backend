package com.cg.movie.services;

import java.util.Set;

import com.cg.movie.entities.Movie;

public interface IMovieService {
	
    Movie addMovie(Movie movie);

	Set<Movie> findAllMovie();

	void deleteMovieById(long movieId);

	String searchMovie(String movie);
	
    boolean findMovieById(long movieId);

}
