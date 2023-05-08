package com.autorizador.dto;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.lang.NonNull;


public class TransacaoDTO {
	
	private BigDecimal valor;
	
	@NonNull
	private Long numeroCartao;
	private String senhaCartao;
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public Long getNumeroCartao() {
		return numeroCartao;
	}
	
	public void setNumeroCartao(Long numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getSenhaCartao() {
		return senhaCartao;
	}

	public void setSenhaCartao(String senhaCartao) {
		this.senhaCartao = senhaCartao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numeroCartao, senhaCartao, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransacaoDTO other = (TransacaoDTO) obj;
		return Objects.equals(numeroCartao, other.numeroCartao) && Objects.equals(senhaCartao, other.senhaCartao)
				&& Objects.equals(valor, other.valor);
	}
	
}
