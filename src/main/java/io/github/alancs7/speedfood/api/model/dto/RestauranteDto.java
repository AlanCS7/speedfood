package io.github.alancs7.speedfood.api.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.github.alancs7.speedfood.api.model.view.RestauranteView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteDto {

    @Schema(description = "ID do restaurante", example = "1")
    @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
    private Long id;

    @Schema(description = "Nome do restaurante", example = "Thai Gourmet")
    @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
    private String nome;

    @Schema(description = "Taxa de entrega do restaurante", example = "10")
    @JsonView(RestauranteView.Resumo.class)
    private BigDecimal taxaFrete;

    @JsonView(RestauranteView.Resumo.class)
    private CozinhaDto cozinha;

    @Schema(description = "Status do restaurante", example = "true")
    private Boolean ativo;

    @Schema(description = "Status do restaurante", example = "true")
    private Boolean aberto = Boolean.TRUE;

    private EnderecoDto endereco;
}
