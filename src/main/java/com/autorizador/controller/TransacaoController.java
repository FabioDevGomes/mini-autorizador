package com.autorizador.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	public TransacaoDTO debitarValor(@RequestBody TransacaoDTO transacao) {
		return null;
	}
}
