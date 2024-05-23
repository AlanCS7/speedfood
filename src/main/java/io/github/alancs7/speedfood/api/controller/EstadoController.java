package io.github.alancs7.speedfood.api.controller;

import io.github.alancs7.speedfood.api.mapper.EstadoMapper;
import io.github.alancs7.speedfood.api.model.dto.EstadoDto;
import io.github.alancs7.speedfood.api.model.input.EstadoInput;
import io.github.alancs7.speedfood.api.openapi.controller.EstadoControllerOpenApi;
import io.github.alancs7.speedfood.domain.model.Estado;
import io.github.alancs7.speedfood.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController implements EstadoControllerOpenApi {

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private EstadoMapper mapper;

    @GetMapping
    public List<EstadoDto> listar() {
        return mapper.toCollectionDto(estadoService.listar());
    }

    @GetMapping("/{id}")
    public EstadoDto buscar(@PathVariable Long id) {
        return mapper.toDto(estadoService.buscarOuFalhar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoDto adicionar(@RequestBody @Valid EstadoInput estadoInput) {
        Estado estado = mapper.toDomainObject(estadoInput);

        return mapper.toDto(estadoService.salvar(estado));
    }

    @PutMapping("/{id}")
    public EstadoDto atualizar(@PathVariable Long id, @RequestBody @Valid EstadoInput estadoInput) {
        Estado estadoAtual = estadoService.buscarOuFalhar(id);

        mapper.copyToDomainObject(estadoInput, estadoAtual);

        return mapper.toDto(estadoService.salvar(estadoAtual));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        estadoService.excluir(id);
    }
}
