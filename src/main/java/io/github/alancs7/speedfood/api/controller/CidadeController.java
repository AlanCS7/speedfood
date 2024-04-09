package io.github.alancs7.speedfood.api.controller;

import io.github.alancs7.speedfood.api.mapper.CidadeMapper;
import io.github.alancs7.speedfood.api.model.dto.CidadeDto;
import io.github.alancs7.speedfood.api.model.input.CidadeInput;
import io.github.alancs7.speedfood.domain.exception.BusinessException;
import io.github.alancs7.speedfood.domain.exception.EstadoNotFoundException;
import io.github.alancs7.speedfood.domain.model.Cidade;
import io.github.alancs7.speedfood.domain.service.CidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @ApiOperation("Lista as cidades")
    @GetMapping
    public List<CidadeDto> listar() {
        return mapper.toCollectionDto(cidadeService.listar());
    }

    @ApiOperation("Busca uma cidade por ID")
    @GetMapping("/{id}")
    public CidadeDto buscar(@ApiParam(value = "ID de uma cidade", example = "1")
                            @PathVariable Long id) {
        return mapper.toDto(cidadeService.buscarOuFalhar(id));
    }

    @ApiOperation("Cadastra uma cidade")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeDto adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade")
                               @RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidade = mapper.toDomainObject(cidadeInput);
            return mapper.toDto(cidadeService.salvar(cidade));
        } catch (EstadoNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @ApiOperation("Atualiza uma cidade por ID")
    @PutMapping("/{id}")
    public CidadeDto atualizar(@ApiParam(value = "ID de uma cidade", example = "1")
                               @PathVariable Long id,
                               @ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados")
                               @RequestBody @Valid CidadeInput cidadeInput) {
        Cidade cidadeAtual = cidadeService.buscarOuFalhar(id);

        mapper.copyToDomainObject(cidadeInput, cidadeAtual);

        try {
            return mapper.toDto(cidadeService.salvar(cidadeAtual));
        } catch (EstadoNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @ApiOperation("Exclui uma cidade por ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@ApiParam(value = "ID de uma cidade", example = "1")
                        @PathVariable Long id) {
        cidadeService.excluir(id);
    }
}
