package io.github.alancs7.speedfood.api.openapi.controller;

import io.github.alancs7.speedfood.api.exception.ApiError;
import io.github.alancs7.speedfood.api.model.dto.GrupoDto;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@Api(tags = "Usuarios")
public interface UsuarioGrupoControllerOpenApi {

    @Operation(
            summary = "Lista os grupos associados a um usuário",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "Usuário não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))

                    )
            }
    )
    List<GrupoDto> listar(@Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId);

    @Operation(
            summary = "Associação de grupo com usuário",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Associação realizada com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Usuário ou grupo não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    void associar(@Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId,
                  @Parameter(description = "ID do grupo", example = "1", required = true) Long grupoId);

    @Operation(
            summary = "Desassociação de grupo com usuário",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Desassociação realizada com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Usuário ou grupo não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    void desassociar(@Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId,
                     @Parameter(description = "ID do grupo", example = "1", required = true) Long grupoId);

}
