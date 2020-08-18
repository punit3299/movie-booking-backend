package com.cg.movie.response;

import java.sql.Timestamp;

public class ShowResponseVO {

	private Long showId;
	private String showName;
	private String showLanguage;
	private String movieName;
	private Timestamp showStartTime;
	private Timestamp showEndTime;
	private String theatreName;
	private String screenName;
	
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
	public Long getShowId() {
		return showId;
	}
	public void setShowId(Long showId) {
		this.showId = showId;
	}
}
