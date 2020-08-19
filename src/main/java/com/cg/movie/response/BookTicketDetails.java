package com.cg.movie.response;

import java.sql.Timestamp;

public class BookTicketDetails {

    private String seatNo;
    private Long showId;
    private Long movieId;
    private Long screenId;
    private Long theatreId;
    private Long customerId;
    private String cityName;
    private double ticketPrice;
    private Timestamp bookingDate,showDate;
    

	
	public BookTicketDetails() {
		super();
	}

	public BookTicketDetails(String seatNo, Long showId, Long movieId, Long screenId, Long theatreId, Long customerId,
			String cityName, double ticketPrice, Timestamp bookingDate, Timestamp showDate) {
		super();
		this.seatNo = seatNo;
		this.showId = showId;
		this.movieId = movieId;
		this.screenId = screenId;
		this.theatreId = theatreId;
		this.customerId = customerId;
		this.cityName = cityName;
		this.ticketPrice = ticketPrice;
		this.bookingDate = bookingDate;
		this.showDate = showDate;
	}

	public Timestamp getShowDate() {
		return showDate;
	}
	public void setShowDate(Timestamp showDate) {
		this.showDate = showDate;
	}
	public Timestamp getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Timestamp bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	public Long getShowId() {
		return showId;
	}
	public void setShowId(Long showId) {
		this.showId = showId;
	}
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	public Long getScreenId() {
		return screenId;
	}
	public void setScreenId(Long screenId) {
		this.screenId = screenId;
	}
	public Long getTheatreId() {
		return theatreId;
	}
	public void setTheatreId(Long theatreId) {
		this.theatreId = theatreId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public double getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
    
	
}
