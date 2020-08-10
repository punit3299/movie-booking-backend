package com.cg.movie.services;

import com.cg.movie.entities.Show;

public interface IShowService {

	public Show addShow(Show show);

	public Long addNewShow(long theatreId, long screenId, long movieId, Show show);
}
