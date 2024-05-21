package io.github.alancs7.speedfood.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class PedidoDto {

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

    @Schema(description = "Data e hora da confirmação do pedido", example = "2024-05-21T05:33:51Z")
    private OffsetDateTime dataConfirmacao;

    @Schema(description = "Data e hora da entrega do pedido", example = "2024-05-21T50:33:51Z")
    private OffsetDateTime dataEntrega;

    @Schema(description = "Data e hora do cancelamento do pedido", example = "2024-05-21T08:33:51Z")
    private OffsetDateTime dataCancelamento;

    private RestauranteResumoDto restaurante;

    private UsuarioDto cliente;

    private FormaPagamentoDto formaPagamento;

    private EnderecoDto enderecoEntrega;

    private List<ItemPedidoDto> itens;

}
