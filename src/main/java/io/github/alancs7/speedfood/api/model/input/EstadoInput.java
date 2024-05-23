package io.github.alancs7.speedfood.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EstadoInput {

    @Schema(description = "Nome do estado", example = "São Paulo")
    @NotBlank
    private String nome;
}
