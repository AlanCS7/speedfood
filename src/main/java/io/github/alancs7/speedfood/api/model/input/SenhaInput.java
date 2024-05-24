package io.github.alancs7.speedfood.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SenhaInput {

    @Schema(description = "Senha atual do usuário/cliente", example = "123")
    @NotBlank
    private String senhaAtual;

    @Schema(description = "Nova senha do usuário/cliente", example = "1234")
    @NotBlank
    private String novaSenha;

}
