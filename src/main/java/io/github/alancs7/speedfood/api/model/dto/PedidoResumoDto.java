package io.github.alancs7.speedfood.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class PedidoResumoDto {

    @Schema(description = "Código de um pedido", example = "981c8bd3-c170-11ee-80e5-00155d4d44fe")
    private String codigo;

    @Schema(description = "Subtotal do pedido", example = "79")
    private BigDecimal subtotal;

    @Schema(description = "Taxa de entrega do pedido", example = "10")
    private BigDecimal taxaFrete;

    @Schema(description = "Valor total do pedido", example = "89")
    private BigDecimal valorTotal;

    @Schema(description = "Status do pedido", example = "CRIADO")
    private String status;

    @Schema(description = "Data e hora da criação do pedido", example = "2024-05-21T03:33:51Z")
    private OffsetDateTime dataCriacao;

    private RestauranteResumoDto restaurante;

    private UsuarioDto cliente;

}
