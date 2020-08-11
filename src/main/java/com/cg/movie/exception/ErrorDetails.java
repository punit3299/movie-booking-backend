package com.cg.movie.exception;



import org.springframework.http.HttpStatus;

public class ErrorDetails {


	private String message;
	private HttpStatus header;
	
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getHeader() {
		return header;
	}

	public void setHeader(HttpStatus header) {
		this.header = header;
	}

	public ErrorDetails() {
	}

	public ErrorDetails(String message, HttpStatus header) {
		super();
		this.message = message;
		this.header = header;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getHeader() {
		return header;
	}

	public void setHeader(HttpStatus header) {
		this.header = header;
	}


}
