package com.autorizador.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.autorizador.dto.CartaoDTO;
import com.autorizador.entity.CartaoEntity;
import com.autorizador.mock.CartaoMock;
import com.autorizador.repository.CartaoRepository;
import com.autorizador.service.CartaoService;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CartaoServiceTest {
	
	private CartaoMock cartaoMock;
	
	@InjectMocks
	private CartaoService cartaoService;
	
	@Mock
	private CartaoRepository cartaoRepository;
	
	@BeforeEach
	void setUp() throws Exception {
		cartaoMock = new CartaoMock();
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testCriarCartao() {
		CartaoDTO cartaoDTOSemSaldo = cartaoMock.mockDTOSemSaldoInicialEId();
		CartaoEntity cartaoEntity = cartaoMock.mockEntity();
		
		when(cartaoRepository.save(any())).thenReturn(cartaoEntity);
		
		CartaoDTO resultado = cartaoService.criarCartao(cartaoDTOSemSaldo);
		
		assertEquals(1L, resultado.getId());
		assertEquals(cartaoDTOSemSaldo.getSenha(), resultado.getSenha());
		assertEquals(cartaoDTOSemSaldo.getNumeroCartao(), resultado.getNumeroCartao());
		assertEquals(new BigDecimal(500.00), resultado.getSaldo());
	}

	@Test
	void retornarSaldo() {
		CartaoEntity cartaoEntity = cartaoMock.mockEntity();
		
		when(cartaoRepository.findByNumeroCartao(any())).thenReturn(Optional.of(cartaoEntity));
		
		BigDecimal resultado = cartaoService.retornarSaldo(any(Long.class));

		assertEquals(new BigDecimal(500), resultado);
	}
}
