package com.cg.movie.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="city_table")
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cityId;
	private String cityName;

	@JsonIgnore
	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
	private Set<Theatre> theatresList = new HashSet<>();
	
	public City()
	{
		
	}


	public City(Long cityId, String cityName) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@JsonIgnore
	public Set<Theatre> getTheatresList() {
		return theatresList;
	}

	public void setTheatresList(Set<Theatre> theatresList) {
		this.theatresList = theatresList;
	}

	// the method below will add Theatre to city
	// also serves the purpose to avoid cyclic references.
	public void addTheatre(Theatre theatre) {
		theatre.setCity(this); // this will avoid nested cascade
		this.getTheatresList().add(theatre);
	}

}
