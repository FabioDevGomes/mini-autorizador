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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.autorizador.dto.TransacaoDTO;
import com.autorizador.entity.CartaoEntity;
import com.autorizador.mock.CartaoMock;
import com.autorizador.mock.TransacaoMock;
import com.autorizador.repository.CartaoRepository;
import com.autorizador.service.TransacaoService;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TransacaoServiceTest {
	
	private CartaoMock cartaoMock;
	private TransacaoMock transacaoMock;
	
	@InjectMocks
	private TransacaoService transacaoService;
	
	@Mock
	private CartaoRepository cartaoRepository;

	@Mock
	private BCryptPasswordEncoder encoder;
	
	@BeforeEach
	void setUp() throws Exception {
		cartaoMock = new CartaoMock();
		transacaoMock = new TransacaoMock();
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testRalizarDebito() {
		CartaoEntity cartaoEntity = cartaoMock.mockEntity();
		TransacaoDTO dto = transacaoMock.mockDTO();
		
		when(cartaoRepository.findByNumeroCartao(any())).thenReturn(Optional.of(cartaoEntity));
		when(encoder.matches(any(String.class), any(String.class))).thenReturn(true);
		
		BigDecimal novoSaldo = transacaoService.realizarDebito(dto);

		assertEquals(new BigDecimal(400), novoSaldo);
	}
}
