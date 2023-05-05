package com.autorizador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autorizador.dto.CartaoDTO;
import com.autorizador.dto.TransacaoDTO;
import com.autorizador.entity.CartaoEntity;
import com.autorizador.exception.SaldoInsuficienteException;
import com.autorizador.repository.CartaoRepository;

@Service
public class TransacaoService {
	
	private static String MSG_SALDO_INSUFICIENTE = "SALDO_INSUFICIENTE";
	
	@Autowired
	CartaoService cartaoService;
	
	@Autowired
	CartaoRepository cartaoRepository;
	
	public void realizarDebito(TransacaoDTO transacao) {
		CartaoDTO dto = getCartao(transacao);
		
		debitarValor(transacao, dto);
		
		try {
			cartaoRepository.save(cartaoService.parseToEntity(dto));
		} catch (Exception e) {
			e.printStackTrace();
			throw new SaldoInsuficienteException(MSG_SALDO_INSUFICIENTE);
		}
	}

	private void debitarValor(TransacaoDTO transacao, CartaoDTO dto) {
		Long novoSaldo = dto.getSaldo() - transacao.getValor();
		dto.setSaldo(novoSaldo);
	}

	private CartaoDTO getCartao(TransacaoDTO transacao) {
		CartaoEntity cartaoEntity = cartaoService.buscarCartao(transacao.getNumeroCartao());
		CartaoDTO dto = cartaoService.parseToDTO(cartaoEntity);
		return dto;
	}

}
