package com.cg.movie.exception;

public class SeatAlreadyBookedException extends RuntimeException {
	
	public SeatAlreadyBookedException(String message) {
		super(message);
	}

}
