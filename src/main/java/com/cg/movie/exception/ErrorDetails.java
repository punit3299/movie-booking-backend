package com.cg.movie.exception;

import javax.xml.ws.spi.http.HttpHandler;

import org.springframework.http.HttpStatus;

public class ErrorDetails {

	private String message;
	private HttpStatus header;

	public ErrorDetails() {
	}

	public ErrorDetails(String message, HttpStatus header) {
		super();
		this.message = message;
		this.header = header;
	}

}
