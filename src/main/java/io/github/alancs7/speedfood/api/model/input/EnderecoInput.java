package io.github.alancs7.speedfood.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EnderecoInput {

    @Schema(description = "Cep do endereço", example = "38400-111")
    @NotBlank
    private String cep;

    @Schema(description = "Logradouro do endereço", example = "Rua Acre")
    @NotBlank
    private String logradouro;

    @Schema(description = "Número do endereço", example = "300")
    @NotBlank
    private String numero;

    @Schema(description = "Complemento do endereço", example = "Casa 2")
    private String complemento;

    @Schema(description = "Bairro do endereço", example = "Centro")
    @NotBlank
    private String bairro;

    @Schema(description = "ID da cidade", example = "1")
    @NotNull
    private Long cidade;
}
