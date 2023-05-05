package com.autorizador.exception;

public class SaldoInsuficienteException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public SaldoInsuficienteException(String ms) {
		super(ms);
	}

}
