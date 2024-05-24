package io.github.alancs7.speedfood.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioInput {

    @Schema(description = "ID do usuário/cliente", example = "1")
    @NotBlank
    private String nome;

    @Schema(description = "Email do usuário/cliente", example = "joao.ger@speedfood.com.br")
    @NotBlank
    @Email
    private String email;

}
