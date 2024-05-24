package io.github.alancs7.speedfood.api.openapi.controller;

import io.github.alancs7.speedfood.api.exception.ApiError;
import io.github.alancs7.speedfood.api.model.dto.UsuarioDto;
import io.github.alancs7.speedfood.api.model.input.SenhaInput;
import io.github.alancs7.speedfood.api.model.input.UsuarioComSenhaInput;
import io.github.alancs7.speedfood.api.model.input.UsuarioInput;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@Api(tags = "Usuarios")
public interface UsuarioControllerOpenApi {

    @Operation(
            summary = "Lista os usuários"
    )
    List<UsuarioDto> listar();

    @Operation(
            summary = "Busca um usuário por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "400",
                            description = "ID do usuário inválido",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Usuário não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    UsuarioDto buscar(@Parameter(description = "ID do usuário", example = "1", required = true) Long id);

    @Operation(
            summary = "Cadastra um usuário",
            responses = @ApiResponse(responseCode = "201", description = "Usuário cadastrado")
    )
    UsuarioDto adicionar(@Parameter(description = "Representação de um novo usuário", required = true) UsuarioComSenhaInput usuarioComSenhaInput);

    @Operation(
            summary = "Atualiza um usuário por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Usuário atualizado"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Usuário não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    UsuarioDto atualizar(@Parameter(description = "ID do usuário", example = "1", required = true) Long id,
                         @Parameter(description = "Representação de um usuário com os novos dados", required = true) UsuarioInput usuarioInput);

    @Operation(
            summary = "Atualiza a senha de um usuário",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Senha alterada com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Usuário não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    void alterarSenha(@Parameter(description = "ID do usuário", example = "1", required = true) Long id,
                      @Parameter(description = "Representação de uma nova senha", required = true) SenhaInput senhaInput);

}