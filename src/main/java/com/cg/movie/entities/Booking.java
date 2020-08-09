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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="booking_table")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;
	private Timestamp bookingDate;
	private Double totalCost;

	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Booking(Long bookingId, Timestamp bookingDate, Double totalCost) {
		super();
		this.bookingId = bookingId;
		this.bookingDate = bookingDate;
		this.totalCost = totalCost;
	}



	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "transactionId")
	private Transaction transaction;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ticketId")
	private Ticket ticket;

	@ManyToOne
	@JoinColumn(name = "movieId")
	private Movie movie;

	@ManyToOne
	@JoinColumn(name = "showId")
	private Show show;

	@JsonIgnore
	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
	private Set<Seat> seatsList = new HashSet<>();

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public Timestamp getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Timestamp bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	@JsonIgnore
	public Set<Seat> getSeatsList() {
		return seatsList;
	}

	public void setSeatsList(Set<Seat> seatsList) {
		this.seatsList = seatsList;
	}

	// the method below will add seat to booking
	// also serves the purpose to avoid cyclic references.
	public void addSeat(Seat seat) {
		seat.setBooking(this); // this will avoid nested cascade
		this.getSeatsList().add(seat);
	}

}
