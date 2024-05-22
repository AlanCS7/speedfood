package io.github.alancs7.speedfood.api.openapi.controller;

import io.github.alancs7.speedfood.api.exception.ApiError;
import io.github.alancs7.speedfood.api.model.dto.RestauranteDto;
import io.github.alancs7.speedfood.api.model.input.RestauranteInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {

    @ApiImplicitParam(
            value = "Nome da projecão de restaurantes",
            name = "projecao",
            allowableValues = "apenas-nome",
            paramType = "query",
            dataTypeClass = String.class
    )
    @Operation(
            summary = "Lista os restaurantes"
    )
    List<RestauranteDto> listar();

    @Operation(
            summary = "Lista os restaurantes",
            ignoreJsonView = true
    )
    List<RestauranteDto> listarApenasNome();

    @Operation(
            summary = "Busca um restaurante por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "400",
                            description = "ID do restaurante inválido",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Restaurante não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    RestauranteDto buscar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long id);

    @Operation(
            summary = "Cadastra um restaurante",
            responses = @ApiResponse(responseCode = "201", description = "Restaurante cadastrado")
    )
    RestauranteDto adicionar(@Parameter(name = "corpo", description = "Representação de um novo restaurante") RestauranteInput restauranteInput);

    @Operation(
            summary = "Atualiza um restaurante por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Restaurante atualizado"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Restaurante não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    RestauranteDto atualizar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long id,
                             @Parameter(name = "corpo", description = "Representação de um restaurante com os novos dados") RestauranteInput restauranteInput);

    @Operation(
            summary = "Ativa um restaurante por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Restaurante ativado"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Restaurante não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    void ativar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long id);

    @Operation(
            summary = "Inativa um restaurante por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Restaurante inativado"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Restaurante não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    void inativar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long id);

    @Operation(
            summary = "Ativa múltiplos restaurantes",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Restaurantes ativados"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Restaurante não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    void ativarEmMassa(@Parameter(description = "IDs dos restaurantes", example = "[1, 2, 3]", required = true) List<Long> ids);

    @Operation(
            summary = "Inativa múltiplos restaurantes",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Restaurantes inativados"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Restaurante não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    void inativarEmMassa(@Parameter(description = "IDs dos restaurantes", example = "[1, 2, 3]", required = true) List<Long> ids);

    @Operation(
            summary = "Abre um restaurante por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Restaurante aberto"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Restaurante não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    void abrir(@Parameter(description = "ID do restaurante", example = "1", required = true) Long id);

    @Operation(
            summary = "Fecha um restaurante por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Restaurante fechado"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Restaurante não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    void fechar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long id);
}
