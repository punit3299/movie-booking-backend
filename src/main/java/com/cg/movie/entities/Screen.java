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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="screen_table")
public class Screen {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long screenId;
	private String screenName;
	private int noOfSeats;
	private boolean status;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "movieId")
	private Movie movie;

	@JsonIgnore
	@OneToMany(mappedBy = "screen", cascade = CascadeType.ALL)
	private Set<Show> showsList = new HashSet<>();

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "theaterId")
	private Theatre theatre;

	
	
	public Screen() {
	}

	

	public Screen(Long screenId, String screenName, int noOfSeats, boolean status) {
		super();
		this.screenId = screenId;
		this.screenName = screenName;
		this.noOfSeats = noOfSeats;
		this.status = status;
	}



	public Long getScreenId() {
		return screenId;
	}

	public void setScreenId(Long screenId) {
		this.screenId = screenId;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	@JsonIgnore
	public Set<Show> getShowsList() {
		return showsList;
	}

	public void setShowsList(Set<Show> showsList) {
		this.showsList = showsList;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	// the method below will add show to screen
	// also serves the purpose to avoid cyclic references.
	public void addShow(Show show) {
		show.setScreen(this); // this will avoid nested cascade
		this.getShowsList().add(show);
	}

	@Override
	public String toString() {
		return "Screen [screenId=" + screenId + ", screenName=" + screenName + ", noOfSeats=" + noOfSeats + ", status="
				+ status +  "]";
	}

	
}
