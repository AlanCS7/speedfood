package io.github.alancs7.speedfood.api.openapi.controller;

import io.github.alancs7.speedfood.api.exception.ApiError;
import io.github.alancs7.speedfood.api.model.dto.UsuarioDto;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@Api(tags = "Restaurantes")
public interface RestauranteUsuarioResponsavelControllerOpenApi {

    @Operation(
            summary = "Lista os usuários responsáveis associados a restaurante",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "Restaurante não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    List<UsuarioDto> listar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId);

    @Operation(
            summary = "Associação de restaurante com usuário responsável",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Associação realizada com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Restaurante ou usuário não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    void associar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId,
                  @Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId);

    @Operation(
            summary = "Desassociação de restaurante com usuário responsável",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Desassociação realizada com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Restaurante ou usuário não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    void desassociar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId,
                     @Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId);
}
