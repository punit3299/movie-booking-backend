package com.cg.movie.services;

import java.util.List;

import com.cg.movie.entities.Theatre;

public interface ITheatreService {
	
	public Theatre addTheatre(Theatre theatre);

	public void deleteTheatre(Theatre theatre);
	public void updateTheatre(Theatre theatre);
	public List<Theatre> viewAllTheatre();
	public Theatre getTheatreById(long threatreId);
	public List<Theatre> searchTheater(String theatre);
}
