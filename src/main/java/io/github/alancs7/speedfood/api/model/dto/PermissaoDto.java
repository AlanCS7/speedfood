package io.github.alancs7.speedfood.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoDto {

    @Schema(description = "ID da permissão", example = "1")
    private Long id;

    @Schema(description = "Nome da permissão", example = "CONSULTAR_COZINHAS")
    private String nome;

    @Schema(description = "Descrição da permissão", example = "Permite consultar cozinhas")
    private String descricao;
}
