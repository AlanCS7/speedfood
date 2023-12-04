package io.github.alancs7.speedfood.domain.repository;

import io.github.alancs7.speedfood.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryQueries {

    List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
}
