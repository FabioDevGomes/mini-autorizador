package com.autorizador.exception;

public class RecursoNaoEncontradoException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public RecursoNaoEncontradoException(String ms) {
		super(ms);
	}

}
