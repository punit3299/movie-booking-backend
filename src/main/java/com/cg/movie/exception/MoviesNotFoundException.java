package com.cg.movie.exception;

@SuppressWarnings("serial")
public class MoviesNotFoundException extends RuntimeException {

	public MoviesNotFoundException(String message) {
		super(message);
	}

}
