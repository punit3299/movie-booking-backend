package com.cg.movie.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.movie.dao.MovieRepository;
import com.cg.movie.dao.ScreenRepository;
import com.cg.movie.dao.TheatreRepository;
import com.cg.movie.entities.Movie;
import com.cg.movie.entities.Screen;
import com.cg.movie.entities.Theatre;

@Component
public class ShowValidator {
	
	@Autowired
	TheatreRepository theatreRepo;
	
	@Autowired
	ScreenRepository screenRepo;
	
	@Autowired
	MovieRepository movieRepo;
	
	public Theatre validateTheatreId(long theatreId)
	{
		Theatre theatre=theatreRepo.findById(theatreId).get();
		if(theatre==null)
		{
			
		}
		return theatre;
	}
	
	public Screen validateScreenId(long screenId)
	{
		Screen screen=screenRepo.findById(screenId).get();
		if(screen==null)
		{
			
		}
		return screen;
	}
	
	public Movie validateMovieId(long movieId)
	{
		Movie movie=movieRepo.findByMovieId(movieId);
		if(movie==null)
		{
			
		}
		return movie;
	}

}
