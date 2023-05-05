package com.autorizador.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	public CartaoDTO criarCartao(@RequestBody CartaoDTO cartao) {
		return cartaoService.criarCartao(cartao);
	}

	@GetMapping(value = "/{numeroCartao}")
	public Long obterSaldo(@PathVariable("numeroCartao") Long numeroCartao) {
		return cartaoService.retornarSaldo(numeroCartao);
	}
	
}
