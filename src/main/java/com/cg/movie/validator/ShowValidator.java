package com.cg.movie.validator;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.movie.dao.MovieRepository;
import com.cg.movie.dao.ScreenRepository;
import com.cg.movie.dao.ShowRepository;
import com.cg.movie.dao.TheatreRepository;
import com.cg.movie.entities.Movie;
import com.cg.movie.entities.Screen;
import com.cg.movie.entities.Show;
import com.cg.movie.entities.Theatre;
import com.cg.movie.exception.InValidDataEntryException;
import com.cg.movie.exception.MoviesNotFoundException;
import com.cg.movie.exception.ScreenNotFoundException;
import com.cg.movie.exception.ShowOverlapException;
import com.cg.movie.exception.TheatreNotFoundException;

/******************************************************************************************************************
 * 
 * @author Prabhjot 
 * 
 * Description :It provides the methods validateThearteId,validateMovieId,validateScreenId ,validateShowTimePeriod
 *              for validating the data.
 * 
 *         created by : Prabhjot , 10 August 2020
 *
 ******************************************************************************************************************/

@Component
public class ShowValidator {

	@Autowired
	TheatreRepository theatreRepo;

	@Autowired
	ScreenRepository screenRepo;

	@Autowired
	MovieRepository movieRepo;

	@Autowired
	ShowRepository showRepo;

	public Theatre validateTheatreId(long theatreId) {

		Theatre theatre = theatreRepo.findTheatreById(theatreId);
		if (theatre==null) {
			throw new TheatreNotFoundException("Theatre with id" + " " + theatreId + "doesn't exist");
		}
		return theatre;
	}

	public Screen validateScreenId(long screenId) {
		Screen screen = screenRepo.findByScreenId(screenId);
		if (screen==null) {
			throw new ScreenNotFoundException("Screen with id" + " " + screenId + " " + "doesn't exist");
		}
		return screen;
	}

	public Movie validateMovieName(String movieName) {
		Movie movie = movieRepo.findMovieByName(movieName);
		if (movie==null) {
			throw new MoviesNotFoundException("Movie with name" + " " + movieName + " " + "Not Found");
		}
		return movie;
	}

	public void validateShowTimePeriod(Timestamp startTime, Timestamp endTime, long screenId) {
		if (!endTime.after(startTime) || startTime.before(new Date())) {
			throw new InValidDataEntryException("Please enter valid date and time");
		}
		List<Show> overlappingShows = showRepo.timePeriodOverlap(startTime, endTime, screenId);

		if (!overlappingShows.isEmpty()) {
			throw new ShowOverlapException(
					"Show Already booked in this time Period." + "Please Select the Start time and End time again.");
		}
	}

}
