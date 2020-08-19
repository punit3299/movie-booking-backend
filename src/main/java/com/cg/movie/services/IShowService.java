package com.cg.movie.services;

import java.util.List;
import java.util.Set;

import com.cg.movie.entities.Show;
import com.cg.movie.request.ShowRequestVO;
import com.cg.movie.response.ShowResponseVO;

public interface IShowService {

	Long addNewShow(ShowRequestVO showRequestVO);

    void deleteShowById(long showId);

    Set<ShowResponseVO> getAllShow(Long theatreId ,Long screenId);
	
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
