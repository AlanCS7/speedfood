package io.github.alancs7.speedfood.api.controller;

import io.github.alancs7.speedfood.api.exception.ApiError;
import io.github.alancs7.speedfood.api.mapper.CidadeMapper;
import io.github.alancs7.speedfood.api.model.dto.CidadeDto;
import io.github.alancs7.speedfood.api.model.input.CidadeInput;
import io.github.alancs7.speedfood.domain.exception.BusinessException;
import io.github.alancs7.speedfood.domain.exception.EstadoNotFoundException;
import io.github.alancs7.speedfood.domain.model.Cidade;
import io.github.alancs7.speedfood.domain.service.CidadeService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Cidades")
@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private CidadeMapper mapper;

    @Operation(summary = "Lista as cidades")
    @GetMapping
    public List<CidadeDto> listar() {
        return mapper.toCollectionDto(cidadeService.listar());
    }

    @Operation(summary = "Busca uma cidade por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID da cidade inválido", content = {
                    @Content(schema = @Schema(implementation = ApiError.class))
            }),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = {
                    @Content(schema = @Schema(implementation = ApiError.class))
            })
    })
    @GetMapping("/{id}")
    public CidadeDto buscar(@Parameter(description = "ID de uma cidade", example = "1")
                            @PathVariable Long id) {
        return mapper.toDto(cidadeService.buscarOuFalhar(id));
    }

    @Operation(summary = "Cadastra uma cidade")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cidade cadastrada")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeDto adicionar(@Parameter(name = "corpo", description = "Representação de uma nova cidade")
                               @RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidade = mapper.toDomainObject(cidadeInput);
            return mapper.toDto(cidadeService.salvar(cidade));
        } catch (EstadoNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Operation(summary = "Atualiza uma cidade por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cidade atualizada"),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = {
                    @Content(schema = @Schema(implementation = ApiError.class))
            })
    })
    @PutMapping("/{id}")
    public CidadeDto atualizar(@Parameter(description = "ID de uma cidade", example = "1")
                               @PathVariable Long id,
                               @Parameter(name = "corpo", description = "Representação de uma cidade com os novos dados")
                               @RequestBody @Valid CidadeInput cidadeInput) {
        Cidade cidadeAtual = cidadeService.buscarOuFalhar(id);

        mapper.copyToDomainObject(cidadeInput, cidadeAtual);

        try {
            return mapper.toDto(cidadeService.salvar(cidadeAtual));
        } catch (EstadoNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Operation(summary = "Exclui uma cidade por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cidade excluída"),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = {
                    @Content(schema = @Schema(implementation = ApiError.class))
            })
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@Parameter(description = "ID de uma cidade", example = "1")
                        @PathVariable Long id) {
        cidadeService.excluir(id);
    }
}
