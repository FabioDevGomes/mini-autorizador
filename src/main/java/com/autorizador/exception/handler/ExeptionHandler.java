package com.autorizador.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.autorizador.exception.CartaoInexistenteException;
import com.autorizador.exception.RecursoNaoEncontradoException;
import com.autorizador.exception.SaldoInsuficienteException;
import com.autorizador.exception.SenhaInvalidaException;

@ControllerAdvice
@RestController
public class ExeptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(RecursoNaoEncontradoException.class)
	public final ResponseEntity<?> handlerRecursoNaoEncontradoException(Exception ex, WebRequest request){
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(CartaoInexistenteException.class)
	public final ResponseEntity<?> handlerCartaoInexistenteException(Exception ex, WebRequest request){
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
	}

	@ExceptionHandler(SaldoInsuficienteException.class)
	public final ResponseEntity<?> handlerSaldoInsuficienteException(Exception ex, WebRequest request){
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
	}

	@ExceptionHandler(SenhaInvalidaException.class)
	public final ResponseEntity<?> handlerSenhaInvalidaException(Exception ex, WebRequest request){
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
	}
	
}
