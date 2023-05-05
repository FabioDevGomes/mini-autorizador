package com.autorizador.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Range;

@Entity
public class CartaoEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	private String senha;
	
	@Column(unique = true)
	private Long numeroCartao;
	
	@Range(min = 0)
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

}
