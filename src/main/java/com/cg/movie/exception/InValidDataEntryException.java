package com.cg.movie.exception;

@SuppressWarnings("serial")
public class InValidDataEntryException extends RuntimeException {

	public InValidDataEntryException(String message) {
		super(message);
	}
}
