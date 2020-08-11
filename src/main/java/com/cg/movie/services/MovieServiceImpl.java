package com.cg.movie.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		Movie movie1 = movieRepo.save(movie);
		return movie1;
	}

	/********************************************************************************
	 * 
	 * Method : findAllMovies Description: for fetching the movie list.
	 * 
	 * @return Movie list i.e movieList If Movies avaliable otherwise throws
	 *         MoviesNotFoundException Exception.
	 * 
	 *                                 Created by: Prabhjot ,9 August 2020
	 * 
	 **********************************************************************************/

	@Override
	public Set<Movie> findAllMovie() throws MoviesNotFoundException {
		List<Movie> movieList = movieRepo.findAll();
		Set<Movie> movieList1 = new HashSet<>(movieList);
		return movieList1;
	}

	/********************************************************************************
	 * 
	 * Method : deleteMovieById
	 * 
	 * Description: for deleting the movie by changing the status to true.
	 * 
	 * @throws MovieDoesntExistException : It is raised if movieId doesn't exist.
	 * 
	 *                                   Created by: Prabhjot ,9 August 2020
	 * 
	 **********************************************************************************/

	@Override
	public void deleteMovieById(long movieId) throws MovieDoesntExistException {
		if (movieRepo.existsById(movieId)) {
			movieRepo.deleteMovieById(true, movieId);
		} else
			throw new MoviesNotFoundException("Movie with" + movieId + "doesn't Exist");
	}

}
