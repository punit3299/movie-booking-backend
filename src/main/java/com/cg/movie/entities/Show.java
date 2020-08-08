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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "show_table")
public class Show {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long showId;
	private Timestamp showStartTime;
	private Timestamp showEndTime;
	private String showName;

	@JsonIgnore
	@OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
	private Set<Booking> bookingsList = new HashSet<Booking>();

	@JsonIgnore
	@OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
	private Set<Seat> seatsList = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
	private Set<Transaction> transactionsList = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "theatreId")
	private Theatre theatre;

	@ManyToOne
	@JoinColumn(name = "screenId")
	private Screen screen;

	@ManyToOne
	@JoinColumn(name = "movieId")
	private Movie movie;

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
	public Set<Booking> getBookingsList() {
		return bookingsList;
	}

	public void setBookingsList(Set<Booking> bookingsList) {
		this.bookingsList = bookingsList;
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

	// the method below will add booking to show
	// also serves the purpose to avoid cyclic references.
	public void addBooking(Booking booking) {
		booking.setShow(this); // this will avoid nested cascade
		this.getBookingsList().add(booking);
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
