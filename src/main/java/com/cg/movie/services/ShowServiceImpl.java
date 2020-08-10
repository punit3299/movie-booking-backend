package com.cg.movie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.MovieRepository;
import com.cg.movie.dao.ShowRepository;
import com.cg.movie.entities.Movie;
import com.cg.movie.entities.Screen;
import com.cg.movie.entities.Show;
import com.cg.movie.entities.Theatre;
import com.cg.movie.validator.ShowValidator;

@Service
public class ShowServiceImpl implements IShowService {
	@Autowired
	ShowRepository showRepo;

	@Autowired
	MovieRepository movieRepo;

	@Autowired
	ShowValidator showValidator;

	@Override
	public Show addShow(Show show) {

		return showRepo.save(show);
	}

	@Override
	public Long addNewShow(long theatreId, long screenId, long movieId, Show show) {
		
		Movie movie = showValidator.validateMovieId(movieId);
		Theatre theatre = showValidator.validateTheatreId(theatreId);
		Screen screen = showValidator.validateScreenId(screenId);

		showValidator.validateShowTimePeriod(show.getShowStartTime(), show.getShowEndTime(), screen.getScreenId());
		show.setMovie(movie);
		show.setTheatre(theatre);
		show.setScreen(screen);

		Show addShow = showRepo.save(show);
		return addShow.getShowId();
	}


}
