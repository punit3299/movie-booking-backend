package com.cg.movie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.movie.dao.CityRepository;
import com.cg.movie.entities.City;
import com.cg.movie.services.CityServiceImpl;

@SpringBootTest
public class CityTest {
	
	@Autowired
	CityServiceImpl cityService;
	
	@MockBean
	CityRepository repository;
	
	@Test
	public void viewAllCitiesTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new City(new Long(1), "BBK"), new City(new Long(2), "LKO")).collect(Collectors.toList()));
		assertEquals(2, cityService.viewAllCity().size());
	}
	
	@Test
	public void addCity()
	{
		City city=new City(1L,"BBK");
		when(repository.save(city)).thenReturn(city);
		assertEquals(city, cityService.addCity(city));
	}
}
