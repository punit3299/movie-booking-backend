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
	
	/********************************************************************************
	 * 
	 * Method : findScreenById
	 * 
	 * Description: To check whether screen exists
	 * 
	 * @param  : screenId 		Screen screenId
	 *
	 * @return : boolean
	 * 
	 * @throw ScreenNotFoundException : It is raised when screenId doesn't exists
	 * 
	 *         Created by: Saurav Suman ,9 August 2020
	 * 
	 **********************************************************************************/
	@Override
	public boolean findScreenById(long screenId) throws ScreenNotFoundException {
		if(screenRepo.existsById(screenId)) {
			return true;
		}
		else {
			logger.error("Screen not found with "+screenId);
			throw new ScreenNotFoundException("Screen Not Found");
		}
	}
	
	/********************************************************************************
	 * 
	 * Method : addScreen
	 * 
	 * Description: for adding the screen.
	 * 
	 * @param  : theatreId 		Theatre theatreId
	 * @param  : screenDetails 	Screen screenDetails
	 * 
	 * @throw TheatreNotFoundException : It is raised when theatreId doesn't exist  
	 * 
	 * @return : Screen Entity
	 * 
	 *         Created by: Saurav Suman ,9 August 2020
	 * 
	 **********************************************************************************/

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


	/********************************************************************************
	 * 
	 * Method : getAllScreen
	 * 
	 * Description: for fetching all the screen.
	 * 
	 * @param  : theatreId 		Theatre theatreId
	 * 
	 * @throw TheatreNotFoundException : It is raised when theatreId doesn't exist  
	 * 
	 * @return : List of Screen Entity
	 * 
	 *         Created by: Saurav Suman ,9 August 2020
	 * 
	 **********************************************************************************/
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

	/********************************************************************************
	 * 
	 * Method : deleteScreen
	 * 
	 * Description: for deleting screen 
	 * 
	 * @param  : screenId 		Screen screenId
	 *
	 * @return : boolean
	 * 
	 *         Created by: Saurav Suman ,10 August 2020
	 * 
	 **********************************************************************************/
	@Override
	public boolean deleteScreen(long screenId) throws ScreenNotFoundException {
		
		    findScreenById(screenId);
			screenRepo.deleteScreenById( screenId);
			logger.info("Delete screen of id "+screenId);
			return true;

	}

	/********************************************************************************
	 * 
	 * Method : addSeats
	 * 
	 * Description: for adding seats in particular screen 
	 * 
	 * @param  : screenId 		Screen screenId
	 * @param  : noOfSeats		int noOfSeats
	 *
	 * @return : int noOfSeats
	 * 
	 * @throw InvalidAttributeException : t is raised when noOfSeats is 0 or less than that.
	 * 
	 *         Created by: Saurav Suman ,10 August 2020
	 * 
	 **********************************************************************************/

	@Override
	public int addSeats(long screenId, int noOfSeats) throws ScreenNotFoundException {
		
		findScreenById(screenId);
			
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

	/********************************************************************************
	 * 
	 * Method : updateSeats
	 * 
	 * Description: for updating seats in particular screen 
	 * 
	 * @param  : screenId 		Screen screenId
	 * @param  : noOfSeats		int noOfSeats
	 *
	 * @return : int updatedNoOfSeats
	 * 
	 * @throw InvalidAttributeException : It is raised when noOfSeats is 0 or less than that.
	 * 
	 *         Created by: Saurav Suman ,11 August 2020
	 * 
	 **********************************************************************************/

	@Override
	public int updateNoOfSeats(long screenId, int noOfSeats) throws ScreenNotFoundException {
		
		findScreenById(screenId);
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

	/********************************************************************************
	 * 
	 * Method : getNoOfSeats
	 * 
	 * Description: for fetching no of seats in particular screen 
	 * 
	 * @param  : screenId 		Screen screenId
	 *
	 * @return : int noOfSeats
	 * 
	 *         Created by: Saurav Suman ,11 August 2020
	 * 
	 **********************************************************************************/
	@Override
	public int getNoOfSeats(long screenId) throws ScreenNotFoundException {
		
		findScreenById(screenId);
		
		Screen screen = screenRepo.findById(screenId).get();
		logger.info("Returned No of seats of screen of id "+screenId);
		return screen.getNoOfSeats();
		

		
	}


	
}