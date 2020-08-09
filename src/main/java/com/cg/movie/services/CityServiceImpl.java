package com.cg.movie.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.CityRepository;
import com.cg.movie.entities.City;
import com.cg.movie.entities.Theatre;
@Service
public class CityServiceImpl implements ICityService {

	@Autowired
	CityRepository cityRepo;
	
	@Override
	public City addCity(City city) {
		return cityRepo.save(city);
	}

	@Override
	public List<City> viewAllCity() {
		List<City> cities= cityRepo.findAll();
		return cities;
	}

	@Override
	public List<Theatre> getAllTheatreByCity(String city) {
		// TODO Auto-generated method stub
		City requiredCity= (City) cityRepo.findByCityName(city);
		int n=requiredCity.getTheatresList().size();
	    List<Theatre> theatreList = new ArrayList<Theatre>(n); 
	    for (Theatre x : requiredCity.getTheatresList()) 
	      theatreList.add(x);
	    return theatreList;
	}

}