package io.github.alancs7.speedfood.api.controller;

import io.github.alancs7.speedfood.api.controller.openapi.GrupoControllerOpenApi;
import io.github.alancs7.speedfood.api.mapper.GrupoMapper;
import io.github.alancs7.speedfood.api.model.dto.GrupoDto;
import io.github.alancs7.speedfood.api.model.input.GrupoInput;
import io.github.alancs7.speedfood.domain.model.Grupo;
import io.github.alancs7.speedfood.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private GrupoMapper mapper;

    @GetMapping
    public List<GrupoDto> listar() {
        return mapper.toCollectionDto(grupoService.listar());
    }

    @GetMapping("/{id}")
    public GrupoDto buscar(@PathVariable Long id) {
        return mapper.toDto(grupoService.buscarOuFalhar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoDto adicionar(@RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = mapper.toDomainObject(grupoInput);

        return mapper.toDto(grupoService.salvar(grupo));
    }

    @PutMapping("/{id}")
    public GrupoDto atualizar(@PathVariable Long id, @RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupoAtual = grupoService.buscarOuFalhar(id);

        mapper.copyToDomainObject(grupoInput, grupoAtual);

        return mapper.toDto(grupoService.salvar(grupoAtual));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        grupoService.excluir(id);
    }
}
