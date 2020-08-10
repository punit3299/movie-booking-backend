package com.cg.movie.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.ScreenRepository;
import com.cg.movie.dao.TheatreRepository;
import com.cg.movie.entities.Screen;
import com.cg.movie.entities.Theatre;

@Service
public class ScreenServiceImpl implements IScreenService {

	@Autowired
	ScreenRepository screenRepo;
	
	@Autowired
	TheatreRepository theatreRepo;

	@Override
	public Screen addScreen(long theatreId, Screen screenDetails) {
		Theatre theatre=theatreRepo.findById(theatreId).get();
		screenRepo.save(screenDetails);
		theatre.addScreen(screenDetails);
		theatreRepo.save(theatre);
		return screenDetails;
		
	}

	@Override
	public List<Screen> getAllScreen(long theatreId) {
		List<Screen> allScreenDetails = screenRepo.findAll(true,theatreId);
		return allScreenDetails;
	}

	@Override
	public boolean deleteScreen(long screenId) {
		if (screenRepo.existsById(screenId)) {
			screenRepo.deleteScreenById(true, screenId);
			return true;
		} else
			return false;

	}

	@Override
	public int addSeats(long screenId, int noOfSeats) {
		Screen screen = screenRepo.findById(screenId).get();
		screen.setNoOfSeats(noOfSeats);
		screenRepo.save(screen);
		return screen.getNoOfSeats();

	}

	@Override
	public int updateNoOfSeats(long screenId, int noOfSeats) {
		Screen screen = screenRepo.findById(screenId).get();
		int updatedSeat=screen.getNoOfSeats()+noOfSeats;
		screen.setNoOfSeats(updatedSeat);
		screenRepo.save(screen);
		return screen.getNoOfSeats();
	}

	@Override
	public int getNoOfSeats(long screenId) {
		Screen screen = screenRepo.findById(screenId).get();
		return screen.getNoOfSeats();
	}

	
}
