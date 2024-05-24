package io.github.alancs7.speedfood.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {

    @Schema(description = "ID do usuário/cliente", example = "1")
    private Long id;

    @Schema(description = "Nome do usuário/cliente", example = "João da Silva")
    private String nome;

    @Schema(description = "Email do usuário/cliente", example = "joao.ger@speedfood.com.br")
    private String email;

}
