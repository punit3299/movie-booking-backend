package com.cg.movie.exception;

@SuppressWarnings("serial")
public class ShowDoesntExistException extends RuntimeException {
	public ShowDoesntExistException(String message) {
		super(message);
	}
}
