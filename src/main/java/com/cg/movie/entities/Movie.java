package com.cg.movie.entities;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="movie_table")
public class Movie {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="genName1")
	@SequenceGenerator(name="genName1", sequenceName="mov",initialValue=1170000011,allocationSize=1)
	private Long movieId;
	private String movieName;
	private String movieGenre;
	private String movieDirector;
	private Double movieLength;
	private Integer movieRating;
	private Timestamp movieReleaseDate;
	private boolean status;

	@OneToOne(mappedBy="movie")
	private Screen screen;

	@JsonIgnore
	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	private Set<Show> showsList = new HashSet<Show>();

	
	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	private Set<Language> languagesList = new HashSet<Language>();
	
	public Movie() {
	}

	public Movie(Long movieId, String movieName, String movieGenre, String movieDirector, Double movieLength,
			Integer movieRating, Timestamp movieReleaseDate) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.movieGenre = movieGenre;
		this.movieDirector = movieDirector;
		this.movieLength = movieLength;
		this.movieRating = movieRating;
		this.movieReleaseDate = movieReleaseDate;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

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

	public void setMovieLength(Double length) {
		this.movieLength = length;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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


	@JsonIgnore
	public Set<Show> getShowsList() {
		return showsList;
	}

	public void setShowsList(Set<Show> showsList) {
		this.showsList = showsList;
	}

	public Set<Language> getLanguagesList() {
		return languagesList;
	}

	public void setLanguagesList(Set<Language> languagesList) {
		this.languagesList = languagesList;
	}

	// the method below will add show to movie
	// also serves the purpose to avoid cyclic references.
	public void addShow(Show show) {
		show.setMovie(this); // this will avoid nested cascade
		this.getShowsList().add(show);
	}

	// the method below will add language to movie
	// also serves the purpose to avoid cyclic references.
	public void addLanguage(Language language) {
		language.setMovie(this); // this will avoid nested cascade
		this.getLanguagesList().add(language);
	}
}