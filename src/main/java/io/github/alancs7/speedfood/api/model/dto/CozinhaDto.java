package io.github.alancs7.speedfood.api.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.github.alancs7.speedfood.api.model.view.RestauranteView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaDto {

    @Schema(description = "ID da cozinha", example = "1")
    @JsonView(RestauranteView.Resumo.class)
    private Long id;

    @Schema(description = "Nome da cozinha", example = "Brasileira")
    @JsonView(RestauranteView.Resumo.class)
    private String nome;

}
