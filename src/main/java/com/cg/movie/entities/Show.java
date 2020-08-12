package com.cg.movie.entities;

import java.sql.Timestamp;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "show_table")
public class Show {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="genName1")
	@SequenceGenerator(name="genName1", sequenceName="sho",initialValue=55156,allocationSize=1)
	private Long showId;
	private Timestamp showStartTime;
	private Timestamp showEndTime;
	private String showName;
	private boolean status;

	@JsonIgnore
	@OneToOne(mappedBy = "show")
	private Booking booking;

	@JsonIgnore
	@OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
	private Set<Seat> seatsList = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
	private Set<Transaction> transactionsList = new HashSet<>();

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "theatreId")
	private Theatre theatre;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "screenId")
	private Screen screen;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "movieId")
	private Movie movie;

	public Show() {
		super();
	}

	public Show(Long showId, Timestamp showStartTime, Timestamp showEndTime, String showName) {
		super();
		this.showId = showId;
		this.showStartTime = showStartTime;
		this.showEndTime = showEndTime;
		this.showName = showName;
	}

	public Long getShowId() {
		return showId;
	}

	public void setShowId(Long showId) {
		this.showId = showId;
	}

	public Timestamp getShowStartTime() {
		return showStartTime;
	}

	public void setShowStartTime(Timestamp showStartTime) {
		this.showStartTime = showStartTime;
	}

	public Timestamp getShowEndTime() {
		return showEndTime;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setShowEndTime(Timestamp showEndTime) {
		this.showEndTime = showEndTime;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	@JsonIgnore
	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	@JsonIgnore
	public Set<Seat> getSeatsList() {
		return seatsList;
	}

	public void setSeatsList(Set<Seat> seatsList) {
		this.seatsList = seatsList;
	}

	@JsonIgnore
	public Set<Transaction> getTransactionsList() {
		return transactionsList;
	}

	public void setTransactionsList(Set<Transaction> transactionsList) {
		this.transactionsList = transactionsList;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	// the method below will add transaction to show
	// also serves the purpose to avoid cyclic references.
	public void addTransaction(Transaction transaction) {
		transaction.setShow(this); // this will avoid nested cascade
		this.getTransactionsList().add(transaction);
	}

	// the method below will add seat to show
	// also serves the purpose to avoid cyclic references.
	public void addSeat(Seat seat) {
		seat.setShow(this); // this will avoid nested cascade
		this.getSeatsList().add(seat);
	}
}