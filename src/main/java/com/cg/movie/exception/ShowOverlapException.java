package com.cg.movie.exception;

@SuppressWarnings("serial")
public class ShowOverlapException extends RuntimeException {
	public ShowOverlapException(String message) {
		super(message);
	}
}
