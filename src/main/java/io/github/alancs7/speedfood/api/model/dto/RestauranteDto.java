package io.github.alancs7.speedfood.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteDto {

    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaDto cozinha;
    private Boolean ativo;
}
