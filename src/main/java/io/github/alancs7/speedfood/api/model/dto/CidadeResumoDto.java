package io.github.alancs7.speedfood.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeResumoDto {

    @Schema(description = "ID da Cidade", example = "1")
    private Long id;

    @Schema(description = "Nome da Cidade", example = "São Paulo")
    private String nome;

    @Schema(description = "Nome do estado", example = "São Paulo")
    private String estado;
}
