package com.autorizador.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.autorizador.dto.CartaoDTO;
import com.autorizador.entity.CartaoEntity;
import com.autorizador.exception.RecursoNaoEncontradoException;
import com.autorizador.mapper.DozerMapper;
import com.autorizador.repository.CartaoRepository;

@Service
public class CartaoService {
	
	private static Long SALDO_INICIAL = 500L;
	private static String MSG_CARTAO_NAO_ENCONTRADO = "CARTAO_INEXISTENTE";
	
	@Autowired
	CartaoRepository repository;
	
	public CartaoDTO criarCartao(CartaoDTO cartaoDTO) {
		CartaoEntity entity = null;
		
		cartaoDTO.setSaldo(SALDO_INICIAL);
		
		CartaoEntity cartao = parseToEntity(cartaoDTO);
		encriptarSenha(cartao);
		
		
		entity = repository.save(cartao);
		
		return parseToDTO(entity);
	}

	private void encriptarSenha(CartaoEntity cartaoDTO) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		cartaoDTO.setSenha(encoder.encode(cartaoDTO.getSenha()));
	}

	public Long retornarSaldo(Long numeroCartao) {
		CartaoDTO dto = null;
		CartaoEntity cartaoEntity = buscarCartao(numeroCartao);
		dto = parseToDTO(cartaoEntity);
		return dto.getSaldo();
	}

	public CartaoEntity buscarCartao(Long numeroCartao) {
		CartaoEntity cartaoEntity = repository.findByNumeroCartao(numeroCartao)
				.orElseThrow(() -> new RecursoNaoEncontradoException(MSG_CARTAO_NAO_ENCONTRADO));
		return cartaoEntity;
	}

	public CartaoEntity parseToEntity(CartaoDTO cartaoDTO) {
		return DozerMapper.parseObject(cartaoDTO, CartaoEntity.class);
	}

	public CartaoDTO parseToDTO(CartaoEntity entity) {
		return DozerMapper.parseObject(entity, CartaoDTO.class);
	}
	
}
