package com.cg.movie.services;

import java.util.List;

import com.cg.movie.entities.Screen;
import com.cg.movie.exception.ScreenNotFoundException;
import com.cg.movie.exception.TheatreNotFoundException;

public interface IScreenService {

	public Screen addScreen(long theatreId,Screen screenDetails) throws TheatreNotFoundException;
	
	public boolean deleteScreen(long screenId) throws ScreenNotFoundException;
	
	public int addSeats(long screenId, int noOfSeats) throws ScreenNotFoundException;;

	List<Screen> getAllScreen(long theatreId)  throws TheatreNotFoundException;
	
	public int updateNoOfSeats(long screenId, int noOfSeats) throws ScreenNotFoundException;;
	
	public int getNoOfSeats(long screenId) throws ScreenNotFoundException;;
	
	
}
