package com.autorizador.dto;

import java.util.Objects;

import org.springframework.lang.NonNull;


public class TransacaoDTO {
	
	private Double valor;
	
	@NonNull
	private Long numeroCartao;
	private String senhaCartao;
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Long getNumeroCartao() {
		return numeroCartao;
	}
	
	public void setNumeroCartao(Long numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(numeroCartao, valor);
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
		return Objects.equals(numeroCartao, other.numeroCartao) && Objects.equals(valor, other.valor);
	}

	public String getSenhaCartao() {
		return senhaCartao;
	}

	public void setSenhaCartao(String senhaCartao) {
		this.senhaCartao = senhaCartao;
	}

}
