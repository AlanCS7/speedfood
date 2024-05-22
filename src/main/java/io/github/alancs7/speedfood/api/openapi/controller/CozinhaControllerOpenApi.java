package io.github.alancs7.speedfood.api.openapi.controller;

import io.github.alancs7.speedfood.api.exception.ApiError;
import io.github.alancs7.speedfood.api.model.dto.CozinhaDto;
import io.github.alancs7.speedfood.api.model.input.CozinhaInput;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {

    @Operation(
            summary = "Lista as cozinhas com paginação"
    )
    Page<CozinhaDto> listar(Pageable pageable);

    @Operation(
            summary = "Busca uma cozinha por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "400",
                            description = "ID da cozinha inválido",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cozinha não encontrada",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    CozinhaDto buscar(@Parameter(description = "ID de uma cozinha", example = "1", required = true) Long id);

    @Operation(
            summary = "Cadastra uma cozinha",
            responses = @ApiResponse(responseCode = "201", description = "Cozinha cadastrada")
    )
    CozinhaDto adicionar(@Parameter(name = "corpo", description = "Representação de uma nova cozinha") CozinhaInput cozinhaInput);

    @Operation(
            summary = "Atualiza uma cozinha por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cozinha atualizada"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cozinha não encontrada",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    CozinhaDto atualizar(@Parameter(description = "ID de uma cozinha", example = "1", required = true) Long id,
                         @Parameter(name = "corpo", description = "Representação de uma cozinha com os novos dados") CozinhaInput cozinhaInput);

    @Operation(
            summary = "Exclui uma cozinha por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Cozinha excluída"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cozinha não encontrada",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    void excluir(@Parameter(description = "ID de uma cozinha", example = "1", required = true) Long id);
}
