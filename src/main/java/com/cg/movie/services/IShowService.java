package com.cg.movie.services;

import java.util.Set;

import com.cg.movie.entities.Show;

public interface IShowService {

	public Show addShow(Show show);

	public Long addNewShow(long theatreId, long screenId, long movieId, Show show);

	public void deleteShowById(long showId);

	public Set<Show> getAllShow();
}
