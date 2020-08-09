package com.cg.movie.services;

import java.util.List;

import com.cg.movie.entities.City;
import com.cg.movie.entities.Theatre;


public interface ICityService {
	
	public City addCity(City city);
	public List<City> viewAllCity();
	public List<Theatre> getAllTheatreByCity(String city);

}
