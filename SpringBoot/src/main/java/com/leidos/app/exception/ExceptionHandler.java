package com.leidos.app.exception;

public class ExceptionHandler extends RuntimeException {
		
	public ExceptionHandler(String errorMessage) {
		super(errorMessage);
	}

}
