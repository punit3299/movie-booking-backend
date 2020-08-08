package com.cg.movie.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ticket_table")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ticketId;
	private Long noOfSeats;
	private String seatName; // make it seatsList
	private Boolean ticketStatus;
	private String screenName;
	
	public Ticket(Long ticketId, Long noOfSeats, String seatName, Boolean ticketStatus, String screenName) {
		super();
		this.ticketId = ticketId;
		this.noOfSeats = noOfSeats;
		this.seatName = seatName;
		this.ticketStatus = ticketStatus;
		this.screenName = screenName;
	}

	@OneToOne(mappedBy = "ticket")
	private Booking booking;
	
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public Long getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(Long noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public String getSeatName() {
		return seatName;
	}

	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}

	public Boolean getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(Boolean ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
