package com.cg.movie.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="theatre_table")
public class Theatre {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long theatreId;
	private String theatreName;
	private Integer theatreRating;
	private String managerName;
	private Long managerContact;

	public Theatre()
	{
		
	}
	
	public Theatre(Long theatreId, String theatreName, Integer theatreRating, String managerName, Long managerContact) {
		super();
		this.theatreId = theatreId;
		this.theatreName = theatreName;
		this.theatreRating = theatreRating;
		this.managerName = managerName;
		this.managerContact = managerContact;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
	private Set<Show> showsList = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
	private Set<Screen> screensList = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "cityId")
	private City city;

	public Long getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(Long theatreId) {
		this.theatreId = theatreId;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public Integer getTheatreRating() {
		return theatreRating;
	}

	public void setTheatreRating(Integer theatreRating) {
		this.theatreRating = theatreRating;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public Long getManagerContact() {
		return managerContact;
	}

	public void setManagerContact(Long managerContact) {
		this.managerContact = managerContact;
	}

	@JsonIgnore
	public Set<Show> getShowsList() {
		return showsList;
	}

	public void setShowsList(Set<Show> showsList) {
		this.showsList = showsList;
	}

	@JsonIgnore
	public Set<Screen> getScreensList() {
		return screensList;
	}

	public void setScreensList(Set<Screen> screensList) {
		this.screensList = screensList;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	// the method below will add screen to theatre
	// also serves the purpose to avoid cyclic references.
	public void addScreen(Screen screen) {
		screen.setTheatre(this); // this will avoid nested cascade
		this.getScreensList().add(screen);
	}

	// the method below will add screen to theatre
	// also serves the purpose to avoid cyclic references.
	public void addShow(Show show) {
		show.setTheatre(this); // this will avoid nested cascade
		this.getShowsList().add(show);
	}

	@Override
	public String toString() {
		return "Theatre [theatreId=" + theatreId + ", theatreName=" + theatreName + ", theatreRating=" + theatreRating
				+ ", managerName=" + managerName + ", managerContact=" + managerContact + "]";
	}

	
}
