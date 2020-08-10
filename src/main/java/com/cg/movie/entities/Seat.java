package com.cg.movie.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="seat_table")
public class Seat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long seatId;
	private String seatNumber;
	private Double seatPrice;
 
	@ManyToOne
	@JoinColumn(name = "bookingId")
	private Booking booking;

	@ManyToOne
	@JoinColumn(name = "showId")
	private Show show;

	

	public Seat(Long seatId, String seatNumber, Double seatPrice) {
		super();
		this.seatId = seatId;
		this.seatNumber = seatNumber;
		this.seatPrice = seatPrice;
	}

	public Long getSeatId() {
		return seatId;
	}

	public void setSeatId(Long seatId) {
		this.seatId = seatId;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Double getSeatPrice() {
		return seatPrice;
	}

	public void setSeatPrice(Double seatPrice) {
		this.seatPrice = seatPrice;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}
	
}
