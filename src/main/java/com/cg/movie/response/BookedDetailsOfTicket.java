package com.cg.movie.response;

import java.sql.Timestamp;

public class BookedDetailsOfTicket {
    private Long bookingId;
    private String cityName;
    private String theatreName;
    private String screenName;
    private String seatNo;
    private String movieName;
    private Timestamp bookingDate, showDate;
    private double totalCost;

	
	public BookedDetailsOfTicket() {
		super();
	}

	public BookedDetailsOfTicket(Long bookingId, String cityName, String theatreName, String screenName, String seatNo,
			String movieName, Timestamp bookingDate, Timestamp showDate, double totalCost) {
		super();
		this.bookingId = bookingId;
		this.cityName = cityName;
		this.theatreName = theatreName;
		this.screenName = screenName;
		this.seatNo = seatNo;
		this.movieName = movieName;
		this.bookingDate = bookingDate;
		this.showDate = showDate;
		this.totalCost = totalCost;
	}

	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getTheatreName() {
		return theatreName;
	}
	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public Timestamp getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Timestamp bookingDate) {
		this.bookingDate = bookingDate;
	}
	public Timestamp getShowDate() {
		return showDate;
	}
	public void setShowDate(Timestamp showDate) {
		this.showDate = showDate;
	}
    

}
