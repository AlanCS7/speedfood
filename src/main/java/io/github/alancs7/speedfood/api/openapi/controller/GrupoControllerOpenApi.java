package io.github.alancs7.speedfood.api.openapi.controller;

import io.github.alancs7.speedfood.api.exception.ApiError;
import io.github.alancs7.speedfood.api.model.dto.GrupoDto;
import io.github.alancs7.speedfood.api.model.input.GrupoInput;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

    @Operation(
            summary = "Lista os grupos"
    )
    List<GrupoDto> listar();

    @Operation(
            summary = "Busca um grupo por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "400",
                            description = "ID do grupo inválido",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Grupo não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    GrupoDto buscar(@Parameter(description = "ID do grupo", example = "1") @PathVariable Long id);

    @Operation(
            summary = "Cadastra um grupo",
            responses = @ApiResponse(responseCode = "201", description = "Grupo cadastrado")
    )
    GrupoDto adicionar(@Parameter(name = "corpo", description = "Representação de um novo grupo") @RequestBody @Valid GrupoInput grupoInput);

    @Operation(
            summary = "Atualiza um grupo por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Grupo atualizado"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Grupo não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    GrupoDto atualizar(@Parameter(description = "ID do grupo", example = "1") @PathVariable Long id,
                       @Parameter(name = "corpo", description = "Representação de um grupo com os novos dados") @RequestBody @Valid GrupoInput grupoInput);

    @Operation(
            summary = "Exclui um grupo por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Grupo excluído"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Grupo não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    void excluir(@Parameter(description = "ID do grupo", example = "1") @PathVariable Long id);
}
