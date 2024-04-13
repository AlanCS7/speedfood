package io.github.alancs7.speedfood.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoDto {

    @Schema(description = "ID do grupo", example = "1")
    private Long id;

    @Schema(description = "Nome do grupo", example = "Gerente")
    private String nome;
}
