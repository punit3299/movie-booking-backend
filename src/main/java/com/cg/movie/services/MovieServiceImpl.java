package com.cg.movie.services;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.MovieRepository;
import com.cg.movie.entities.Language;
import com.cg.movie.entities.Movie;
import com.cg.movie.exception.InValidDataEntryException;
import com.cg.movie.exception.MovieDoesntExistException;
import com.cg.movie.exception.MoviesNotFoundException;
import com.cg.movie.response.MovieResponseVO;

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
	public Movie addMovie(Movie movie) throws MoviesNotFoundException {

		Movie movieByName = movieRepo.findMovieByName(movie.getMovieName());
		if (movieByName == null) {
			if (!movie.getMovieReleaseDate().before(new Date())) {
				movie.setStatus(true);
				Movie movieAdded = movieRepo.save(movie);
				return movieAdded;
			} else
				throw new InValidDataEntryException("Please enter the correct movie release date");
		} else {
			throw new MoviesNotFoundException("This movie name already exist.");
		}
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
	public Set<MovieResponseVO> findAllMovie() {
		List<Movie> movieList = movieRepo.findAllMovies();
		Set<MovieResponseVO> movieResponses = new HashSet<>();
		for (Movie movie : movieList) {
			MovieResponseVO movieResponse = new MovieResponseVO();
			movieResponse.setMovieId(movie.getMovieId());
			movieResponse.setMovieName(movie.getMovieName());
			movieResponse.setMovieLength(movie.getMovieLength());
			movieResponse.setMovieDirector(movie.getMovieDirector());
			movieResponse.setMovieGenre(movie.getMovieGenre());
			movieResponse.setMovieReleaseDate(movie.getMovieReleaseDate());
			movieResponse.setMovieRating(movie.getMovieRating());
			movieResponse.setLanguages(
					movie.getLanguageList().stream().map(Language::getLanguageName).collect(Collectors.toSet()));
			movieResponses.add(movieResponse);
		}
		return movieResponses;
	}

	/********************************************************************************
	 * 
	 * Method : deleteMovieById
	 * 
	 * Description: for deleting the movie by changing the status to true.
	 * 
	 * @throws MovieNotFoundException : It is raised if movieId doesn't exist.
	 * 
	 *                                Created by: Prabhjot ,9 August 2020
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

	// .........I will Edit this again so please leave it...............
	@Override
	public String searchMovie(String movieName) {

		List<Movie> movies = movieRepo.findAll();
		for (Movie movie : movies) {
			if (movie.getMovieName().equals(movieName)) {
				return "Found";
			}
		}
		return "No such movie Present";
	}

	@Override
	public boolean findMovieById(long movieId) throws MovieDoesntExistException {
		if (movieRepo.existsById(movieId)) {
			return true;
		} else {
			logger.error("Movie not found with id: " + movieId);
			throw new MovieDoesntExistException("Movie Not Found");
		}
	}

}
