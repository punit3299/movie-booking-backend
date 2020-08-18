package com.cg.movie.services;

import java.util.Set;

import com.cg.movie.entities.Movie;
import com.cg.movie.exception.MoviesNotFoundException;
import com.cg.movie.response.MovieResponseVO;

public interface IMovieService {
	
    Movie addMovie(Movie movie) throws MoviesNotFoundException;

	Set<MovieResponseVO> findAllMovie();

	void deleteMovieById(long movieId);

	String searchMovie(String movie);
	
    boolean findMovieById(long movieId);

}
