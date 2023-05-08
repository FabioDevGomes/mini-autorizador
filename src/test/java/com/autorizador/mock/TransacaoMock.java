package com.autorizador.mock;

import java.math.BigDecimal;

import com.autorizador.dto.CartaoDTO;
import com.autorizador.dto.TransacaoDTO;
import com.autorizador.entity.CartaoEntity;

public class TransacaoMock {
	
	
	public TransacaoDTO mockDTO() {
		TransacaoDTO dto = new TransacaoDTO();
		dto.setNumeroCartao(12345648L);
		dto.setSenhaCartao("123");
		dto.setValor(new BigDecimal(100));
		
		return dto;
	}

}
