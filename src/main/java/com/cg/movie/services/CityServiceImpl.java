package com.cg.movie.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.CityRepository;
import com.cg.movie.entities.City;
import com.cg.movie.entities.Theatre;
import com.cg.movie.exception.CityNotFoundException;
import com.cg.movie.exception.TheatreNotFoundException;
@Service
public class CityServiceImpl implements ICityService {

	@Autowired
	CityRepository cityRepo;
	
	private Logger logger = Logger.getLogger(getClass());
	
	/*
	 * Function to Add city
	 */
	
	@Override
	public City addCity(City city) {
		return cityRepo.save(city);
	}
	
	/*
	 * Function to Get All Cities
	 */

	@Override
	public List<City> viewAllCity() {
		List<City> cities= cityRepo.findAll();
		return cities;
	}

	/*
	 * Function to Get all theatre in a particular city
	 */
	
	@Override
	public List<Theatre> getAllTheatreByCity(String city) {
		// TODO Auto-generated method stub
		City requiredCity= cityRepo.findByCityName(city);
		if(requiredCity==null)
		{
			logger.error("City Not Found");
			throw new CityNotFoundException("City Not Exist");
		}
		else
		{
			logger.info("City Found Successfully");
			int size=requiredCity.getTheatresList().size();
		    
		    if(size==0)
		    {
		    	logger.error("Theatre Not Found For "+ city);
		    	throw new TheatreNotFoundException("Theatre Not Found");
		    }
		    else
		    {
		    	List<Theatre> theatreList = new ArrayList<Theatre>(size);
			    for (Theatre theatre : requiredCity.getTheatresList()) 
				      theatreList.add(theatre);
				    return theatreList;
		    }
		}
	}

}
