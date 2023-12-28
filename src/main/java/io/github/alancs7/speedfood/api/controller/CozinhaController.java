package io.github.alancs7.speedfood.api.controller;

import io.github.alancs7.speedfood.domain.model.Cozinha;
import io.github.alancs7.speedfood.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaService cozinhaService;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaService.listar();
    }

    @GetMapping("/{id}")
    public Cozinha buscar(@PathVariable Long id) {
        return cozinhaService.buscarOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody @Valid Cozinha cozinha) {
        return cozinhaService.salvar(cozinha);
    }

    @PutMapping("/{id}")
    public Cozinha atualizar(@PathVariable Long id, @RequestBody @Valid Cozinha cozinha) {
        Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(id);

        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

        return cozinhaService.salvar(cozinhaAtual);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        cozinhaService.excluir(id);
    }
}
