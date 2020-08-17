package com.cg.movie.response;

import java.sql.Timestamp;
import java.util.Set;

public class MovieResponseVO {
	
	private Long movieId;
	private String movieName;
	private String movieGenre;
	private String movieDirector;
	private Double movieLength;
	private Integer movieRating;
	private Timestamp movieReleaseDate;
	private Set<String> languages;
	
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getMovieGenre() {
		return movieGenre;
	}
	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}
	public String getMovieDirector() {
		return movieDirector;
	}
	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}
	public Double getMovieLength() {
		return movieLength;
	}
	public void setMovieLength(Double movieLength) {
		this.movieLength = movieLength;
	}
	public Integer getMovieRating() {
		return movieRating;
	}
	public void setMovieRating(Integer movieRating) {
		this.movieRating = movieRating;
	}
	public Timestamp getMovieReleaseDate() {
		return movieReleaseDate;
	}
	public void setMovieReleaseDate(Timestamp movieReleaseDate) {
		this.movieReleaseDate = movieReleaseDate;
	}
	public Set<String> getLanguages() {
		return languages;
	}
	public void setLanguages(Set<String> languages) {
		this.languages = languages;
	}
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	
}
