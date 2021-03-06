package com.cg.movie.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ticket_table")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ticketId;
	private String seatName;
	private Boolean ticketStatus;
	private String screenName;


	@JsonIgnore
	@OneToOne(mappedBy = "ticket")
	private Booking booking;

	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;
	
	
	public Ticket(Long ticketId, String seatName, Boolean ticketStatus, String screenName) {
		super();
		this.ticketId = ticketId;
		this.seatName = seatName;
		this.ticketStatus = ticketStatus;
		this.screenName = screenName;
	}

	public Ticket() {
		// TODO Auto-generated constructor stub
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
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

	@JsonIgnore
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