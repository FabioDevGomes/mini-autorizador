package com.autorizador.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.autorizador.dto.CartaoDTO;
import com.autorizador.dto.TransacaoDTO;
import com.autorizador.entity.CartaoEntity;
import com.autorizador.exception.CartaoInexistenteException;
import com.autorizador.exception.SaldoInsuficienteException;
import com.autorizador.exception.SenhaInvalidaException;
import com.autorizador.mapper.DozerMapper;
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

	@Autowired
	BCryptPasswordEncoder encoder;
	
	
	
	public BigDecimal realizarDebito(TransacaoDTO transacao) {
		CartaoDTO dto = getCartao(transacao);
		
		validarSenhaWorkaround(transacao, dto);
		BigDecimal novoSaldo = debitarValor(transacao, dto);
		
		try {
			cartaoRepository.save(DozerMapper.parseObject(dto, CartaoEntity.class));
		} catch (Exception e) {
			e.printStackTrace();
			throw new SaldoInsuficienteException(MSG_SALDO_INSUFICIENTE);
		}
		return novoSaldo;
	}

	private void validarSenhaWorkaround(TransacaoDTO transacao, CartaoDTO dto) {
//		var encoder = new BCryptPasswordEncoder();
		Boolean senhaValida = encoder.matches(transacao.getSenhaCartao(), dto.getSenha());
		switch (Boolean.toString(senhaValida)) {
			case "false": {
				throw new SenhaInvalidaException(SENHA_INVALIDA);
			}
		}
	}

	private BigDecimal debitarValor(TransacaoDTO transacao, CartaoDTO dto) {
		BigDecimal novoSaldo = dto.getSaldo().subtract(transacao.getValor());
		dto.setSaldo(novoSaldo);
		return novoSaldo;
	}

	private CartaoDTO getCartao(TransacaoDTO transacao) {
		CartaoEntity cartaoEntity = cartaoRepository.findByNumeroCartao(transacao.getNumeroCartao())
				.orElseThrow(() -> new CartaoInexistenteException(CARTAO_INEXISTENTE));
		CartaoDTO dto = DozerMapper.parseObject(cartaoEntity, CartaoDTO.class);
		return dto;
	}

}
