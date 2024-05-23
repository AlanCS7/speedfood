package io.github.alancs7.speedfood.api.openapi.controller;

import io.github.alancs7.speedfood.api.exception.ApiError;
import io.github.alancs7.speedfood.api.model.dto.ProdutoDto;
import io.github.alancs7.speedfood.api.model.input.ProdutoInput;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@Api(tags = "Produtos")
public interface RestauranteProdutoControllerOpenApi {

    @Operation(
            summary = "Lista os produtos de um restaurante",
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
    List<ProdutoDto> listar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId,
                            @Parameter(description = "Indica se deve ou não incluir produtos inativos no resultado da listagem", example = "false") boolean incluirInativos);

    @Operation(
            summary = "Busca um produto de um restaurante",
            responses = {
                    @ApiResponse(
                            responseCode = "400",
                            description = "ID do restaurante ou do produto inválido",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Produto de restaurante não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    ProdutoDto buscar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId,
                      @Parameter(description = "ID do produto", example = "1", required = true) Long produtoId);

    @Operation(
            summary = "Cadastra um produto de um restaurante",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Produto cadastrado",
                            content = @Content(schema = @Schema(implementation = ProdutoDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Restaurante não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    ProdutoDto adicionar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId,
                         @Parameter(description = "Representação de um novo produto", example = "1", required = true) ProdutoInput produtoInput);

    @Operation(
            summary = "Atualiza um produto de um restaurante",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Produto atualizado",
                            content = @Content(schema = @Schema(implementation = ProdutoDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Produto de restaurante não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    ProdutoDto atualizar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId,
                         @Parameter(description = "ID do produto", example = "1", required = true) Long produtoId,
                         @Parameter(description = "Representação de um produto com os novos dados", example = "1", required = true) ProdutoInput produtoInput);
}