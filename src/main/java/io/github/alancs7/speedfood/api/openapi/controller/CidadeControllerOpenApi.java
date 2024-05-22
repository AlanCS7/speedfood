package io.github.alancs7.speedfood.api.openapi.controller;

import io.github.alancs7.speedfood.api.exception.ApiError;
import io.github.alancs7.speedfood.api.model.dto.CidadeDto;
import io.github.alancs7.speedfood.api.model.input.CidadeInput;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

    @Operation(
            summary = "Lista as cidades"
    )
    List<CidadeDto> listar();

    @Operation(
            summary = "Busca uma cidade por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "400",
                            description = "ID da cidade inválido",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cidade não encontrada",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    CidadeDto buscar(@Parameter(description = "ID de uma cidade", example = "1", required = true) Long id);

    @Operation(
            summary = "Cadastra uma cidade",
            responses = @ApiResponse(responseCode = "201", description = "Cidade cadastrada")
    )
    CidadeDto adicionar(@Parameter(name = "corpo", description = "Representação de uma nova cidade") CidadeInput cidadeInput);

    @Operation(
            summary = "Atualiza uma cidade por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cidade atualizada"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cidade não encontrada",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    CidadeDto atualizar(@Parameter(description = "ID de uma cidade", example = "1", required = true) Long id,
                        @Parameter(name = "corpo", description = "Representação de uma cidade com os novos dados") CidadeInput cidadeInput);

    @Operation(
            summary = "Exclui uma cidade por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Cidade excluída"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cidade não encontrada",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    void excluir(@Parameter(description = "ID de uma cidade", example = "1", required = true) Long id);
}
