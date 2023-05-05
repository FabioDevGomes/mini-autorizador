package com.autorizador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autorizador.dto.TransacaoDTO;
import com.autorizador.service.TransacaoService;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {
	
	@Autowired
	TransacaoService transacaoService;

	@PostMapping
	public ResponseEntity<?> debitarValor(@RequestBody TransacaoDTO transacao) {
		transacaoService.realizarDebito(transacao);
		return ResponseEntity.status(HttpStatus.CREATED).body("OK");
	}
}
