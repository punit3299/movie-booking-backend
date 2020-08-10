package com.cg.movie.services;

import java.util.List;

import com.cg.movie.entities.Screen;

public interface IScreenService {

	public Screen addScreen(long theatreId,Screen screenDetails);
	
	public boolean deleteScreen(long screenId);
	
	public int addSeats(long screenId, int noOfSeats);

	List<Screen> getAllScreen(long theatreId);
	
	public int updateNoOfSeats(long screenId, int noOfSeats);
	
	public int getNoOfSeats(long screenId);
	
	
}
