package io.github.alancs7.speedfood.api.openapi.controller;

import io.github.alancs7.speedfood.api.exception.ApiError;
import io.github.alancs7.speedfood.api.model.dto.PermissaoDto;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@Api(tags = "Grupos")
public interface GrupoPermissaoControllerOpenApi {

    @Operation(
            summary = "Lista as permissões associadas a um grupo",
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
    List<PermissaoDto> listar(@Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId);

    @Operation(
            summary = "Associação de permissão com grupo",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Associação realizada com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Grupo ou permissão não encontrada",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    void associar(@Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId,
                  @Parameter(description = "ID de uma permissão", example = "1", required = true) Long permissaoId);

    @Operation(
            summary = "Desassociação de permissão com grupo",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Desassociação realizada com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Grupo ou permissão não encontrada",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    void desassociar(@Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId,
                     @Parameter(description = "ID de uma permissão", example = "1", required = true) Long permissaoId);
}
