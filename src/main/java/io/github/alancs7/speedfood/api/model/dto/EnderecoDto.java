package io.github.alancs7.speedfood.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDto {

    @Schema(description = "Cep do endereço", example = "38400-111")
    private String cep;

    @Schema(description = "Logradouro do endereço", example = "Rua Acre")
    private String logradouro;

    @Schema(description = "Número do endereço", example = "300")
    private String numero;

    @Schema(description = "Complemento do endereço", example = "Casa 2")
    private String complemento;

    @Schema(description = "Bairro do endereço", example = "Centro")
    private String bairro;

    private CidadeResumoDto cidade;
}
