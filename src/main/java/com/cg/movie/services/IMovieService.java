package com.cg.movie.services;

import java.util.Set;

import com.cg.movie.entities.Movie;
import com.cg.movie.exception.MovieDoesntExistException;
import com.cg.movie.exception.MoviesNotFoundException;

public interface IMovieService {
	
	public Movie addMovie(Movie movie);

	Set<Movie> findAllMovie();

	void deleteMovieById(long movieId);

}
