package com.autorizador.exception;

public class CartaoInexistenteException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public CartaoInexistenteException(String ms) {
		super(ms);
	}

}
