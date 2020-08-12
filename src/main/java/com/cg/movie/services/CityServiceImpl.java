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
	
	/********************************************************************************
	 * 
	 * Method : addCity
	 * 
	 * Description: for adding the city.
	 * 
	 * @param  : city       City city  
	 * 
	 * @return : City Entity
	 * 
	 *         Created by: Krishna Agarwal ,10 August 2020
	 * 
	 **********************************************************************************/
	
	@Override
	public City addCity(City city) {
		return cityRepo.save(city);
	}

	/********************************************************************************
	 * 
	 * Method : viewAllCity
	 * 
	 * Description: for getting all the available cities.
	 * 
	 * @throw CityNotFoundException : It is raised when City doesn't exist  
	 * 
	 * @return : List of City Entity
	 * 
	 *         Created by: Krishna Agarwal ,9 August 2020
	 * 
	 **********************************************************************************/
	
	@Override
	public List<City> viewAllCity() {
		List<City> cities= cityRepo.findAll();
		if(cities.size()==0) {
			logger.error("No city Found");
			throw new CityNotFoundException("No city added yet");
		}
		 else
		 {
			 logger.info("City found successfully");
			 return cities;
		 }
	}
	
	/********************************************************************************
	 * 
	 * Method : getAllTheatreByCity
	 * 
	 * Description: for getting all the theatre which comes in particular city.
	 * 
	 * @param  : city 		City city
	 * 
	 * @throw CityNotFoundException : It is raised when city doesn't exist
	 * @throw TheatreNotFoundException : It is raised when theatreId doesn't exist  
	 * 
	 * @return : List of theatre Entity
	 * 
	 *         Created by: Krishna Agarwal ,10 August 2020
	 * 
	 **********************************************************************************/

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
			    {
			    	if(!theatre.isStatus()) 
			    	{
			    	theatreList.add(theatre);
			    	}
			    }
				 if(theatreList.size()==0)
				 {
				    	logger.error("No active Theatre Found For "+ city);
				    	throw new TheatreNotFoundException("No active theatre found");
				 }
				 else 
				 {
				  return theatreList;
				  }
		    }
		}
	}
	
	@Override
	public List<City>searchCity(String city) {
		if (cityRepo.findAll()== null)
		{
			throw new CityNotFoundException("City not found");
		}
		List<City> listCity= new ArrayList<City>();
		cityRepo.findAll().forEach(e-> {
			String cityName = e.getCityName();
			if(cityName.equals(city)) {
				listCity.add(e);
			}
		});
		
		return listCity;
	}
	

}
