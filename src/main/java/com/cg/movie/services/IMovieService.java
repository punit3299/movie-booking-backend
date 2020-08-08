package com.cg.movie.services;

import java.util.List;
import java.util.Set;

import com.cg.movie.entities.Movie;

public interface IMovieService {
	
	public Movie addMovie(Movie movie);

	List<Movie> findAllMovie();

	Movie update(Movie movie, int movieId);

	void deleteMovie(Movie movie);

}
