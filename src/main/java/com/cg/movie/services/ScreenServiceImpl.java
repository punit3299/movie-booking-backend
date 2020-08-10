package com.cg.movie.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.ScreenRepository;
import com.cg.movie.dao.TheatreRepository;
import com.cg.movie.entities.Screen;
import com.cg.movie.entities.Theatre;
import com.cg.movie.exception.ScreenNotFoundException;
import com.cg.movie.exception.TheatreNotFoundException;

@Service
public class ScreenServiceImpl implements IScreenService {

	@Autowired
	private ScreenRepository screenRepo;
	
	@Autowired
	private TheatreRepository theatreRepo;

	@Override
	public Screen addScreen(long theatreId, Screen screenDetails) throws TheatreNotFoundException{
		if(theatreRepo.existsById(theatreId))
		{
			Theatre theatre=theatreRepo.findById(theatreId).get();
			screenRepo.save(screenDetails);
			theatre.addScreen(screenDetails);
			theatreRepo.save(theatre);
			return screenDetails;	
		}
		else
			throw new TheatreNotFoundException("Theatre Not Found");
		
	}

	@Override
	public List<Screen> getAllScreen(long theatreId) throws TheatreNotFoundException{
		if(theatreRepo.existsById(theatreId))
		{
		
		List<Screen> allScreenDetails = screenRepo.findAll(true,theatreId);
		return allScreenDetails;
		}
		else
			throw new TheatreNotFoundException("Theatre Not Found");
	}

	@Override
	public boolean deleteScreen(long screenId) throws ScreenNotFoundException {
		if (screenRepo.existsById(screenId)) {
			screenRepo.deleteScreenById(true, screenId);
			return true;
		} else
			throw new ScreenNotFoundException("Screen Not Found");

	}

	@Override
	public int addSeats(long screenId, int noOfSeats) throws ScreenNotFoundException {
		if (screenRepo.existsById(screenId)) {
		Screen screen = screenRepo.findById(screenId).get();
		screen.setNoOfSeats(noOfSeats);
		screenRepo.save(screen);
		return screen.getNoOfSeats();
		}
		else
			throw new ScreenNotFoundException("Screen Not Found");

	}

	@Override
	public int updateNoOfSeats(long screenId, int noOfSeats) throws ScreenNotFoundException {
		if (screenRepo.existsById(screenId)) {
		Screen screen = screenRepo.findById(screenId).get();
		int updatedSeat=screen.getNoOfSeats()+noOfSeats;
		screen.setNoOfSeats(updatedSeat);
		screenRepo.save(screen);
		return screen.getNoOfSeats();
		}
		else
			throw new ScreenNotFoundException("Screen Not Found");

		
	}

	@Override
	public int getNoOfSeats(long screenId) throws ScreenNotFoundException {
		if (screenRepo.existsById(screenId)) {
		Screen screen = screenRepo.findById(screenId).get();
		return screen.getNoOfSeats();
		}
		else
			throw new ScreenNotFoundException("Screen Not Found");

		
	}

	
}
