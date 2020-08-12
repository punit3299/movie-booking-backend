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

		Optional<Theatre> theatre = theatreRepo.findById(theatreId);
		if (!theatre.isPresent()) {
			throw new TheatreNotFoundException("Theatre with id" + " " + theatreId + "doesn't");
		}
		return theatre.get();
	}

	public Screen validateScreenId(long screenId) {
		Optional<Screen> screen = screenRepo.findById(screenId);
		if (!screen.isPresent()) {
			throw new ScreenNotFoundException("Screen with id" + " " + screenId + " " + "doesn't exist");
		}
		return screen.get();
	}

	public Movie validateMovieId(long movieId) {
		Optional<Movie> movie = movieRepo.findById(movieId);
		if (!movie.isPresent()) {
			throw new MoviesNotFoundException("Movie with id" + " " + movieId + " " + "Doesn't exist");
		}
		return movie.get();
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
