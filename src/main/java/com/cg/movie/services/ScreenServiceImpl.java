package com.cg.movie.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import com.cg.movie.dao.ScreenRepository;
import com.cg.movie.dao.TheatreRepository;
import com.cg.movie.entities.Screen;
import com.cg.movie.entities.Theatre;
import com.cg.movie.exception.InvalidAttributeException;
import com.cg.movie.exception.ScreenNotFoundException;
import com.cg.movie.exception.TheatreNotFoundException;

@Service
public class ScreenServiceImpl implements IScreenService {

	@Autowired
	private ScreenRepository screenRepo;
	
	@Autowired
	private TheatreRepository theatreRepo;


	private Logger logger = Logger.getLogger(getClass());
	
	@Override
	public Screen addScreen(long theatreId, Screen screenDetails) throws TheatreNotFoundException{
		if(theatreRepo.existsById(theatreId))
		{	screenDetails.setStatus(true);
			Theatre theatre=theatreRepo.findById(theatreId).get();
			screenRepo.save(screenDetails);
			theatre.addScreen(screenDetails);
			theatreRepo.save(theatre);
			
			logger.info("Added Screen to Theatre of Id "+theatreId );
			return screenDetails;	
		}
		else
		{
			logger.error("Theatre not found with "+theatreId);
			throw new TheatreNotFoundException("Theatre Not Found");
		}
		
	}

	@Override
	public List<Screen> getAllScreen(long theatreId) throws TheatreNotFoundException{
		if(theatreRepo.existsById(theatreId))
		{
		List<Screen> allScreenDetails = screenRepo.findAll(theatreId);
		logger.info("Returned Screen list of theatre "+theatreId);
		return allScreenDetails;
		}
		else
		{
			logger.error("Theatre not found with "+theatreId);
			throw new TheatreNotFoundException("Theatre Not Found");
		}
	}

	@Override
	public boolean deleteScreen(long screenId) throws ScreenNotFoundException {
		if (screenRepo.existsById(screenId)) {
			screenRepo.deleteScreenById( screenId);
			logger.info("Delete screen of id "+screenId);
			return true;
		} else {
			logger.error("Screen not found with "+screenId);
			throw new ScreenNotFoundException("Screen Not Found");
		}

	}

	@Override
	public int addSeats(long screenId, int noOfSeats) throws ScreenNotFoundException {
		if (screenRepo.existsById(screenId)) {
			
		if(noOfSeats > 1)
		{
			Screen screen = screenRepo.findById(screenId).get();
			screen.setNoOfSeats(noOfSeats);
			screenRepo.save(screen);
			logger.info("Added seats in screen of id "+screenId);
			return screen.getNoOfSeats();
		}
		else
		{
			logger.error("Cannot add "+noOfSeats+" seats in the screen");
			throw new InvalidAttributeException("Cannot add "+noOfSeats+" seats in the screen");
		}
		}
		else
		{
			logger.error("Screen not found with "+screenId);
			throw new ScreenNotFoundException("Screen Not Found");
		}
	}

	@Override
	public int updateNoOfSeats(long screenId, int noOfSeats) throws ScreenNotFoundException {
		if (screenRepo.existsById(screenId)) {
			if(noOfSeats > 1)
			{	
		Screen screen = screenRepo.findById(screenId).get();
		int updatedSeat=screen.getNoOfSeats()+noOfSeats;
		screen.setNoOfSeats(updatedSeat);
		screenRepo.save(screen);
		logger.info("Updated no of seat in screen of id "+screenId);
		return screen.getNoOfSeats();
			}
			else
			{
				logger.error("Cannot update "+noOfSeats+" seats in the screen");
				throw new InvalidAttributeException("Cannot update "+noOfSeats+" seats in the screen");
			}
		}
		else
		{
			logger.error("Screen not found with "+screenId);
			throw new ScreenNotFoundException("Screen Not Found");
		}

		
	}

	@Override
	public int getNoOfSeats(long screenId) throws ScreenNotFoundException {
		if (screenRepo.existsById(screenId)) {
		Screen screen = screenRepo.findById(screenId).get();
		logger.info("Returned No of seats of screen of id "+screenId);
		return screen.getNoOfSeats();
		}
		else
		{
			logger.error("Screen not found with "+screenId);
			throw new ScreenNotFoundException("Screen Not Found");
		}

		
	}

	
}