package io.github.alancs7.speedfood.api.openapi.controller;

import io.github.alancs7.speedfood.api.exception.ApiError;
import io.github.alancs7.speedfood.api.model.dto.FormaPagamentoDto;
import io.github.alancs7.speedfood.api.model.input.FormaPagamentoInput;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.List;

@Api(tags = "Formas de Pagamento")
public interface FormaPagamentoControllerOpenApi {

    @Operation(
            summary = "Lista as formas de pagamento"
    )
    ResponseEntity<List<FormaPagamentoDto>> listar(ServletWebRequest request);

    @Operation(
            summary = "Busca uma forma de pagamento por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "400",
                            description = "ID da forma de pagamento inválido",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Forma de pagamento não encontrada",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    ResponseEntity<FormaPagamentoDto> buscar(@Parameter(description = "ID da forma de pagamento", example = "1", required = true) Long id, ServletWebRequest request);

    @Operation(
            summary = "Cadastra uma forma de pagamento",
            responses = @ApiResponse(responseCode = "201", description = "Forma de pagamento cadastrada")
    )
    FormaPagamentoDto adicionar(@Parameter(name = "corpo", description = "Representação de uma nova forma de pagamento") FormaPagamentoInput formaPagamentoInput);

    @Operation(
            summary = "Atualiza uma forma de pagamento por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Forma de pagamento atualizada"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Forma de pagamento não encontrada",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    FormaPagamentoDto atualizar(@Parameter(description = "ID da forma de pagamento", example = "1", required = true) Long id,
                                @Parameter(name = "corpo", description = "Representação de uma forma de pagamento com os novos dados") FormaPagamentoInput formaPagamentoInput);

    @Operation(
            summary = "Exclui uma forma de pagamento por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Forma de pagamento excluída"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Forma de pagamento não encontrada",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    void excluir(@Parameter(description = "ID da forma de pagamento", example = "1", required = true) Long id);

}
