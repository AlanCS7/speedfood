package io.github.alancs7.speedfood.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoDto {

    @Schema(description = "ID do estado", example = "2")
    private Long id;

    @Schema(description = "São Paulo")
    private String nome;
}
