package com.autorizador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.autorizador.dto.CartaoDTO;
import com.autorizador.dto.TransacaoDTO;
import com.autorizador.entity.CartaoEntity;
import com.autorizador.exception.CartaoInexistenteException;
import com.autorizador.exception.SaldoInsuficienteException;
import com.autorizador.exception.SenhaInvalidaException;
import com.autorizador.repository.CartaoRepository;

@Service
public class TransacaoService {
	
	private static final String CARTAO_INEXISTENTE = "CARTAO_INEXISTENTE";
	private static String MSG_SALDO_INSUFICIENTE = "SALDO_INSUFICIENTE";
	private static String SENHA_INVALIDA = "SENHA_INVALIDA";
	
	@Autowired
	CartaoService cartaoService;
	
	@Autowired
	CartaoRepository cartaoRepository;
	
	public void realizarDebito(TransacaoDTO transacao) {
		CartaoDTO dto = getCartao(transacao);
		
		validarSenhaWorkaround(transacao, dto);
		debitarValor(transacao, dto);
		
		try {
			cartaoRepository.save(cartaoService.parseToEntity(dto));
		} catch (Exception e) {
			e.printStackTrace();
			throw new SaldoInsuficienteException(MSG_SALDO_INSUFICIENTE);
		}
	}

	private void validarSenhaWorkaround(TransacaoDTO transacao, CartaoDTO dto) {
		var encoder = new BCryptPasswordEncoder();
		Boolean senhaValida = encoder.matches(transacao.getSenhaCartao(), dto.getSenha());
		switch (Boolean.toString(senhaValida)) {
			case "false": {
				throw new SenhaInvalidaException(SENHA_INVALIDA);
			}
		}
	}

	private void debitarValor(TransacaoDTO transacao, CartaoDTO dto) {
		Double novoSaldo = dto.getSaldo() - transacao.getValor();
		dto.setSaldo(novoSaldo);
	}

	private CartaoDTO getCartao(TransacaoDTO transacao) {
		CartaoEntity cartaoEntity = cartaoRepository.findByNumeroCartao(transacao.getNumeroCartao())
				.orElseThrow(() -> new CartaoInexistenteException(CARTAO_INEXISTENTE));
		CartaoDTO dto = cartaoService.parseToDTO(cartaoEntity);
		return dto;
	}

}
