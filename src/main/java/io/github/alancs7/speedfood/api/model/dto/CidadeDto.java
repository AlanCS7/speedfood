package io.github.alancs7.speedfood.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeDto {

    @Schema(description = "ID da cidade", example = "1")
    private Long id;

    @Schema(description = "Nome da cidade", example = "Campinas")
    private String nome;

    private EstadoDto estado;
}
