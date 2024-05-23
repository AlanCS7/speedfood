package io.github.alancs7.speedfood.api.openapi.controller;

import io.github.alancs7.speedfood.api.exception.ApiError;
import io.github.alancs7.speedfood.api.model.dto.EstadoDto;
import io.github.alancs7.speedfood.api.model.input.EstadoInput;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {

    @Operation(
            summary = "Lista os estados"
    )
    List<EstadoDto> listar();

    @Operation(
            summary = "Busca um estado por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "400",
                            description = "ID do estado inválido",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Estado não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    EstadoDto buscar(@Parameter(description = "ID do estado", example = "1", required = true) Long id);

    @Operation(
            summary = "Cadastra um estado",
            responses = @ApiResponse(responseCode = "201", description = "Estado cadastrado")
    )
    EstadoDto adicionar(@Parameter(name = "corpo", description = "Representação de um novo estado") EstadoInput estadoInput);

    @Operation(
            summary = "Atualiza um estado por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Estado atualizdo"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Estado não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    EstadoDto atualizar(@Parameter(description = "ID do estado", example = "1", required = true) Long id,
                        @Parameter(name = "corpo", description = "Representação de um estado com os novos dados") EstadoInput estadoInput);

    @Operation(
            summary = "Exclui um estado por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Estado excluído"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Estado não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    void excluir(@Parameter(description = "ID do estado", example = "1", required = true) Long id);
}
