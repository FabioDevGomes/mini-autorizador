package com.autorizador.repository;

import org.springframework.stereotype.Repository;

import com.autorizador.entity.CartaoEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface CartaoRepository extends JpaRepository<CartaoEntity, Long>{

	Optional<CartaoEntity> findByNumeroCartao(Long numeroCartao);

}
