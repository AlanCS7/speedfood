package io.github.alancs7.speedfood.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeInput {

    @Schema(description = "Nome da cidade", example = "Campinas", required = true)
    @NotBlank
    private String nome;

    @Schema(description = "ID do estado", example = "2", required = true)
    @NotNull
    private Long estado;
}
