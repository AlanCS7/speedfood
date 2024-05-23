package io.github.alancs7.speedfood.api.openapi.controller;

import io.github.alancs7.speedfood.api.exception.ApiError;
import io.github.alancs7.speedfood.api.model.dto.FormaPagamentoDto;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@Api(tags = "Restaurantes")
public interface RestauranteFormaPagamentoControllerOpenApi {

    @Operation(
            summary = "Lista as formas de pagamento associadas ao restaurante",
            responses = @ApiResponse(
                    responseCode = "404",
                    description = "Restaurante não encontrado",
                    content = @Content(schema = @Schema(implementation = ApiError.class))
            )
    )
    List<FormaPagamentoDto> listar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId);

    @Operation(
            summary = "Associação de restaurante com forma de pagamento",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Associação realizada com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Restaurante ou forma de pagamento não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    void associar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId,
                  @Parameter(description = "ID da forma de pagamento", example = "1", required = true) Long formaPagamentoId);

    @Operation(
            summary = "Desassociação de restaurante com forma de pagamento",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Desassociação realizada com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Restaurante ou forma de pagamento não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    void desassociar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId,
                     @Parameter(description = "ID da forma de pagamento", example = "1", required = true) Long formaPagamentoId);
}
