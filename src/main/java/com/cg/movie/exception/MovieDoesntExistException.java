package com.cg.movie.exception;

public class MovieDoesntExistException extends RuntimeException{


	private static final long serialVersionUID = 1L;

	public MovieDoesntExistException(String message) {
		super(message);
		
	}
	
	 
}
