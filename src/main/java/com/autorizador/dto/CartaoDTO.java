package com.autorizador.dto;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CartaoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	private Long id;
	private String senha;
	private Long numeroCartao;

	@JsonIgnore
	private Long saldo;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Long getNumeroCartao() {
		return numeroCartao;
	}
	
	public void setNumeroCartao(Long numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	
	public Long getSaldo() {
		return saldo;
	}
	
	public void setSaldo(Long saldo) {
		this.saldo = saldo;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, numeroCartao, saldo, senha);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartaoDTO other = (CartaoDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(numeroCartao, other.numeroCartao)
				&& Objects.equals(saldo, other.saldo) && Objects.equals(senha, other.senha);
	}
	
}
