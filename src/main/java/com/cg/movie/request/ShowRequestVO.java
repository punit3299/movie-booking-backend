package com.cg.movie.request;

import java.sql.Timestamp;

import com.sun.istack.NotNull;

public class ShowRequestVO {
	
	private Long theatreId;
	private Long screenId;
	private String movieName;
	private Timestamp showStartTime;
	private Timestamp showEndTime;
	private String showName;
	private String showLanguage;
	
	public Long getTheatreId() {
		return theatreId;
	}
	public void setTheatreId(Long theatreId) {
		this.theatreId = theatreId;
	}
	public Long getScreenId() {
		return screenId;
	}
	public void setScreenId(Long screenId) {
		this.screenId = screenId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
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
	public String getShowLanguage() {
		return showLanguage;
	}
	public void setShowLanguage(String showLanguage) {
		this.showLanguage = showLanguage;
	}
	
	

}
