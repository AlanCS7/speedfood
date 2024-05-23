package io.github.alancs7.speedfood.api.openapi.controller;

import io.github.alancs7.speedfood.api.exception.ApiError;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Api(tags = "Pedidos")
public interface FluxoPedidoControllerOpenApi {

    @Operation(
            summary = "Confirmação de pedido",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Pedido confirmado com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Pedido não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    void confirmar(@Parameter(description = "Código de um pedido", example = "981c8bd3-c170-11ee-80e5-00155d4d44fe", required = true) String codigoPedido);

    @Operation(
            summary = "Confirmação de pedido",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Entrega de pedido registrada com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Pedido não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    void entregar(@Parameter(description = "Código de um pedido", example = "981c8bd3-c170-11ee-80e5-00155d4d44fe", required = true) String codigoPedido);

    @Operation(
            summary = "Confirmação de pedido",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Pedido cancelado com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Pedido não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    void cancelar(@Parameter(description = "Código de um pedido", example = "981c8bd3-c170-11ee-80e5-00155d4d44fe", required = true) String codigoPedido);

}
