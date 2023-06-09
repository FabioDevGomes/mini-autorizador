package com.autorizador.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Range;
import org.springframework.lang.NonNull;

@Entity
public class CartaoEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NonNull
	private String senha;
	
	@NonNull
	@Column(unique = true)
	private Long numeroCartao;
	
	@Range(min = 0)
	private BigDecimal saldo;
	
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
	
	public BigDecimal getSaldo() {
		return saldo;
	}
	
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

}
