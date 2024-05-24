package io.github.alancs7.speedfood.api.openapi.controller;

import io.github.alancs7.speedfood.api.exception.ApiError;
import io.github.alancs7.speedfood.api.model.dto.FotoProdutoDto;
import io.github.alancs7.speedfood.api.model.input.FotoProdutoInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(tags = "Produtos")
public interface RestauranteFotoProdutoControllerOpenApi {


    @Operation(
            summary = "Atualiza a foto do produto de um restaurante",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Foto do produto atualizada"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Produto de restaurante não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    FotoProdutoDto atualizarFoto(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId,
                                 @Parameter(description = "ID do produto", example = "1", required = true) Long produtoId,
                                 FotoProdutoInput fotoProdutoInput,
                                 @Parameter(description = "Arquivo da foto do produto (máximo 2MB, apenas JPG e PNG)", required = true) MultipartFile arquivo) throws IOException;

    @ApiOperation(value = "Busca a foto do produto de um restaurante", produces = "application/json, image/jpeg, image/png")
    @Operation(
            summary = "Busca a foto do produto de um restaurante",
            responses = {
                    @ApiResponse(
                            responseCode = "400",
                            description = "ID do restaurante ou do produto inválido",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Foto de produto não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    FotoProdutoDto buscar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId,
                          @Parameter(description = "ID do produto", example = "1", required = true) Long produtoId);

    @Operation(
            summary = "Busca a foto do produto de um restaurante",
            ignoreJsonView = true
    )
    ResponseEntity<?> servirFoto(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId,
                                 @Parameter(description = "ID do produto", example = "1", required = true) Long produtoId,
                                 String acceptHeader) throws HttpMediaTypeNotAcceptableException;

    @Operation(
            summary = "Exclui a foto do produto de um restaurante",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Foto do produto excluída"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "ID do restaurante ou do produto inválido",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Foto de produto não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    void excluir(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId,
                 @Parameter(description = "ID do produto", example = "1", required = true) Long produtoId);

}
