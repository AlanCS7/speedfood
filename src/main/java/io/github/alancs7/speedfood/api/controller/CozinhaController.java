package io.github.alancs7.speedfood.api.controller;

import io.github.alancs7.speedfood.api.mapper.CozinhaMapper;
import io.github.alancs7.speedfood.api.model.dto.CozinhaDto;
import io.github.alancs7.speedfood.api.model.input.CozinhaInput;
import io.github.alancs7.speedfood.domain.model.Cozinha;
import io.github.alancs7.speedfood.domain.service.CozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private CozinhaMapper mapper;

    @GetMapping
    public Page<CozinhaDto> listar(@PageableDefault(size = 10) Pageable pageable) {
        Page<Cozinha> cozinhas = cozinhaService.listar(pageable);

        List<CozinhaDto> cozinhaDtos = mapper.toCollectionDto(cozinhas.getContent());

        return new PageImpl<>(cozinhaDtos, pageable, cozinhas.getTotalElements());
    }

    @GetMapping("/{id}")
    public CozinhaDto buscar(@PathVariable Long id) {
        return mapper.toDto(cozinhaService.buscarOuFalhar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaDto adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinha = mapper.toDomainObject(cozinhaInput);

        return mapper.toDto(cozinhaService.salvar(cozinha));
    }

    @PutMapping("/{id}")
    public CozinhaDto atualizar(@PathVariable Long id, @RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(id);

        mapper.copyToDomainObject(cozinhaInput, cozinhaAtual);

        return mapper.toDto(cozinhaService.salvar(cozinhaAtual));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        cozinhaService.excluir(id);
    }
}
