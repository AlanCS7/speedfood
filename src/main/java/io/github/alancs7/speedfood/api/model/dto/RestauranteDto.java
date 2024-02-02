package io.github.alancs7.speedfood.api.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.github.alancs7.speedfood.api.model.view.RestauranteView;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteDto {

    @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
    private Long id;

    @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
    private String nome;

    @JsonView(RestauranteView.Resumo.class)
    private BigDecimal taxaFrete;

    @JsonView(RestauranteView.Resumo.class)
    private CozinhaDto cozinha;

    private Boolean ativo;
    private Boolean aberto = Boolean.TRUE;
    private EnderecoDto endereco;
}
