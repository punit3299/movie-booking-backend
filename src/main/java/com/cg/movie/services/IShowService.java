package com.cg.movie.services;

import java.util.List;
import java.util.Set;

import com.cg.movie.entities.Show;

public interface IShowService {

	Long addNewShow(long theatreId, long screenId, long movieId, Show show);

    void deleteShowById(long showId);

	Set<Show> getAllShow(long theatreId);
	
    public List<Show> getShowByMovieId(Long id);
	
	public List<Show> getShowByTheatreId(Long id);
	
	public boolean verifyTheatreId(Long id);
	
	public boolean verifyMovieId(Long id);
	
	public boolean findShowById(long showId);

	List<Show> getAllShows();

}
