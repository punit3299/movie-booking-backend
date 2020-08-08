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
		List<Screen> allScreenDetails=screenRepo.findAll();
		return allScreenDetails;
	}

	@Override
	public void deleteScreen(Screen screen) {
		screenRepo.delete(screen);
		
	}

	@Override
	public int addSeats(long screenId, int noOfSeats) {
		Screen screen=screenRepo.findById(screenId).get();
		screen.setNoOfSeats(noOfSeats);
		System.out.println(screen);
		System.out.println("Hello This is saurav");
		System.out.println(screen.getNoOfSeats());
		return screen.getNoOfSeats();
		
	}
	
	
	

}
