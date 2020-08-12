package com.cg.movie.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.MovieRepository;
import com.cg.movie.entities.Movie;

import com.cg.movie.exception.MovieDoesntExistException;
import com.cg.movie.exception.MoviesNotFoundException;

/********************************************************************************
 * 
 * @author Prabhjot Description :It provides the service for add movie,delete
 *         movie and show movies.
 * 
 *         created by : Prabhjot , 9 August 2020
 *
 ********************************************************************************/
@Service
public class MovieServiceImpl implements IMovieService {

	@Autowired
	MovieRepository movieRepo;
	
	private Logger logger = Logger.getLogger(getClass());

	/********************************************************************************
	 * 
	 * Method : addMovie
	 * 
	 * Description: for adding the movie.
	 * 
	 * @return : movie
	 * 
	 *         Created by: Prabhjot ,9 August 2020
	 * 
	 **********************************************************************************/

	@Override
	public Movie addMovie(Movie movie) {
		// movie.setLanguagesList(languagesList);
		Movie movie1 = movieRepo.save(movie);
		return movie1;
	}

	/********************************************************************************
	 * 
	 * Method : findAllMovies Description: for fetching the movie list.
	 * 
	 * @return Movie list i.e movieList It will return the set of movies.
	 * 
	 *         Created by: Prabhjot ,9 August 2020
	 * 
	 **********************************************************************************/

	@Override
	public Set<Movie> findAllMovie()  {
		List<Movie> movieList = movieRepo.findAllMovies();
		Set<Movie> movieList1 = new HashSet<>(movieList);
		return movieList1;
	}

	/********************************************************************************
	 * 
	 * Method : deleteMovieById
	 * 
	 * Description: for deleting the movie by changing the status to true.
	 * 
	 * @throws MovieNotFoundException : It is raised if movieId doesn't exist.
	 * 
	 *                                   Created by: Prabhjot ,9 August 2020
	 * 
	 **********************************************************************************/

	@Override
	public void deleteMovieById(long movieId) {
		if (movieRepo.existsById(movieId)) {
			movieRepo.deleteMovieById(movieId);
		} else {
			throw new MoviesNotFoundException("Movie with" + movieId + "doesn't Exist");
		}
	}
	
	
	 //.........I will Edit this again so please leave it...............
	@Override
	public String searchMovie(String movieName) {
		
		List<Movie> movies=movieRepo.findAll();
		for(Movie movie: movies) {
			if(movie.getMovieName().equals(movieName)) {
				return "Found";
			}
		}
		return "No such movie Present";
	}

	@Override
	public boolean findMovieById(long movieId) throws MovieDoesntExistException {
		if(movieRepo.existsById(movieId)) {
			return true;
		}
		else {
			logger.error("Movie not found with id: "+movieId);
			throw new MovieDoesntExistException("Movie Not Found");
		}
	}

}
