package io.github.alancs7.speedfood.api.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.github.alancs7.speedfood.api.model.view.RestauranteView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaDto {

    @JsonView(RestauranteView.Resumo.class)
    private Long id;

    @JsonView(RestauranteView.Resumo.class)
    private String nome;
}
