package io.github.alancs7.speedfood.api.openapi.controller;

import io.github.alancs7.speedfood.domain.filter.VendaDiariaFilter;
import io.github.alancs7.speedfood.domain.model.dto.VendaDiaria;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(tags = "Estatisticas")
public interface EstatisticasControllerOpenApi {

    @Operation(
            summary = "Consulta estatísticas de vendas diárias",
            parameters = {
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "restauranteId",
                            description = "ID do restaurante", example = "1",
                            schema = @Schema(type = "integer")
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "dataCriacaoInicio",
                            description = "Data/hora inicial da criação do pedido",
                            example = "2024-12-01T00:00:00Z",
                            schema = @Schema(type = "string", format = "date-time")
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "dataCriacaoFim",
                            description = "Data/hora final da criação do pedido",
                            example = "2024-12-02T23:59:59Z",
                            schema = @Schema(type = "string", format = "date-time")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = VendaDiaria.class))
                                    ),
                                    @Content(
                                            mediaType = "application/pdf",
                                            schema = @Schema(type = "string", format = "binary")
                                    )
                            }
                    )
            }
    )
    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter,
                                             String timeOffset);

    @Operation(hidden = true)
    ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filter, String timeOffset);
}
