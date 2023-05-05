package com.autorizador.exception;

public class SenhaInvalidaException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public SenhaInvalidaException(String ms) {
		super(ms);
	}

}
