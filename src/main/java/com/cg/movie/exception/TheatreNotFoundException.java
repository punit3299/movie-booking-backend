package com.cg.movie.exception;

public class TheatreNotFoundException extends RuntimeException{
	
	public TheatreNotFoundException(String message)
	{
		super(message);
	}

}
