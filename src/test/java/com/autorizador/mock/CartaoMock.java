package com.autorizador.mock;

import java.math.BigDecimal;

import com.autorizador.dto.CartaoDTO;
import com.autorizador.entity.CartaoEntity;

public class CartaoMock {
	
	
	public CartaoDTO mockDTOSemSaldoInicialEId() {
		CartaoDTO cartaoDTO = new CartaoDTO();
		cartaoDTO.setSenha("123");
		cartaoDTO.setNumeroCartao(2133132132123L);
		
		return cartaoDTO;
	}

	public CartaoDTO mockDTOComSaldoInicial() {
		CartaoDTO cartaoDTO = new CartaoDTO();
		cartaoDTO.setId(01L);
		cartaoDTO.setSenha("123");
		cartaoDTO.setNumeroCartao(2133132132123L);
		cartaoDTO.setSaldo(new BigDecimal(500));
		
		return cartaoDTO;
	}

	public CartaoEntity mockEntity() {
		CartaoEntity cartao = new CartaoEntity();
		cartao.setId(01L);
		cartao.setSenha("123");
		cartao.setNumeroCartao(2133132132123L);
		cartao.setSaldo(new BigDecimal(500));
		
		return cartao;
	}

}
