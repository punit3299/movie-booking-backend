package com.cg.movie.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.ScreenRepository;

import com.cg.movie.entities.Screen;

@Service
public class ScreenServiceImpl implements IScreenService {

	@Autowired
	ScreenRepository screenRepo;

	@Override
	public Screen addScreen(long theatreId, Screen screenDetails) {
		return screenRepo.save(screenDetails);
	}

	@Override
	public List<Screen> getAllScreen() {
		List<Screen> allScreenDetails = screenRepo.findAll();
		return allScreenDetails;
	}

	@Override
	public boolean deleteScreen(long screenId) {
		if (screenRepo.existsById(screenId)) {
			screenRepo.deleteScreenById(false, screenId);
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

}
