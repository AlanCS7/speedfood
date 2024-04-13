package io.github.alancs7.speedfood.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CozinhaInput {

    @Schema(description = "Nome da cozinha", example = "Brasileira")
    @NotBlank
    private String nome;
}
