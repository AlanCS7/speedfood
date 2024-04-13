package io.github.alancs7.speedfood.api.controller;

import io.github.alancs7.speedfood.api.controller.openapi.CidadeControllerOpenApi;
import io.github.alancs7.speedfood.api.mapper.CidadeMapper;
import io.github.alancs7.speedfood.api.model.dto.CidadeDto;
import io.github.alancs7.speedfood.api.model.input.CidadeInput;
import io.github.alancs7.speedfood.domain.exception.BusinessException;
import io.github.alancs7.speedfood.domain.exception.EstadoNotFoundException;
import io.github.alancs7.speedfood.domain.model.Cidade;
import io.github.alancs7.speedfood.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private CidadeMapper mapper;

    @GetMapping
    public List<CidadeDto> listar() {
        return mapper.toCollectionDto(cidadeService.listar());
    }

    @GetMapping("/{id}")
    public CidadeDto buscar(@PathVariable Long id) {
        return mapper.toDto(cidadeService.buscarOuFalhar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeDto adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidade = mapper.toDomainObject(cidadeInput);
            return mapper.toDto(cidadeService.salvar(cidade));
        } catch (EstadoNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public CidadeDto atualizar(@PathVariable Long id, @RequestBody @Valid CidadeInput cidadeInput) {
        Cidade cidadeAtual = cidadeService.buscarOuFalhar(id);

        mapper.copyToDomainObject(cidadeInput, cidadeAtual);

        try {
            return mapper.toDto(cidadeService.salvar(cidadeAtual));
        } catch (EstadoNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        cidadeService.excluir(id);
    }
}
