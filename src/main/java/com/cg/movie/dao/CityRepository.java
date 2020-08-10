package com.cg.movie.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.movie.entities.City;
import com.cg.movie.entities.Theatre;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	@Query("select c from City c where c.cityName=?1")
	City findByCityName(String city);
}
