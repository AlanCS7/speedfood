package io.github.alancs7.speedfood.api.openapi.controller;

import io.github.alancs7.speedfood.api.exception.ApiError;
import io.github.alancs7.speedfood.api.model.dto.PedidoDto;
import io.github.alancs7.speedfood.api.model.dto.PedidoResumoDto;
import io.github.alancs7.speedfood.api.model.input.PedidoInput;
import io.github.alancs7.speedfood.domain.filter.PedidoFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

    @ApiImplicitParam(
            value = "Nomes das propiedades para filtrar na resposta, separados por virgula",
            name = "fields",
            paramType = "query",
            dataTypeClass = String.class
    )
    @Operation(
            summary = "Pesquisa os pedidos"
    )
    Page<PedidoResumoDto> pesquisar(PedidoFilter filter, Pageable pageable);


    @ApiImplicitParam(
            value = "Nomes das propiedades para filtrar na resposta, separados por virgula",
            name = "fields",
            paramType = "query",
            dataTypeClass = String.class
    )
    @Operation(
            summary = "Busca um pedido por código",
            responses = {
                    @ApiResponse(
                            responseCode = "400",
                            description = "Código do pedido inválido",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Pedido não encontrado",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    PedidoDto buscar(@Parameter(description = "Código de um pedido", example = "981c8bd3-c170-11ee-80e5-00155d4d44fe") String codigoPedido);

    @Operation(
            summary = "Cadastra um pedido",
            responses = @ApiResponse(responseCode = "201", description = "Pedido cadastrado")
    )
    PedidoDto adicionar(@Parameter(name = "corpo", description = "Representação de um novo pedido") PedidoInput pedidoInput);

}
