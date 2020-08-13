package com.cg.movie.services;

import java.util.List;
import java.util.Set;

import com.cg.movie.entities.Show;

public interface IShowService {

	Long addNewShow(long theatreId, long screenId, long movieId, Show show);

    void deleteShowById(long showId);

	Set<Show> getAllShow(long theatreId);
	
	/*
	 * This method fetches shows associated with a movie by using movieId. It returns'
	 * a list of shows to the user
	 */
    public List<Show> getShowByMovieId(Long id);
	
    /*
	 * This method fetches shows associated with a theater by using theaterId. It returns'
	 * a list of shows to the user
	 */
	public List<Show> getShowByTheatreId(Long id);
	
	/*
	 * This method fetches all the shows for the customer
	 */
	public List<Show> getAllShows();

	public void findShowById(Long showId);

}
