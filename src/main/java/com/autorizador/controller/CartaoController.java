package com.autorizador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autorizador.dto.CartaoDTO;
import com.autorizador.service.CartaoService;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {
	
	@Autowired
	CartaoService cartaoService;
	
	@PostMapping
	public ResponseEntity<?> criarCartao(@RequestBody CartaoDTO cartao) {
		try {
			cartaoService.criarCartao(cartao);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(cartao);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(cartao);
	}

	@GetMapping(value = "/{numeroCartao}")
	public Long obterSaldo(@PathVariable("numeroCartao") Long numeroCartao) {
		return cartaoService.retornarSaldo(numeroCartao);
	}
	
}
