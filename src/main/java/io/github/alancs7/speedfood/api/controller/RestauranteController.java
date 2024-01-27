package io.github.alancs7.speedfood.api.controller;

import io.github.alancs7.speedfood.api.mapper.RestauranteMapper;
import io.github.alancs7.speedfood.api.model.dto.RestauranteDto;
import io.github.alancs7.speedfood.api.model.input.RestauranteInput;
import io.github.alancs7.speedfood.domain.exception.BusinessException;
import io.github.alancs7.speedfood.domain.exception.CidadeNotFoundException;
import io.github.alancs7.speedfood.domain.exception.CozinhaNotFoundException;
import io.github.alancs7.speedfood.domain.model.Restaurante;
import io.github.alancs7.speedfood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private RestauranteMapper mapper;

    @GetMapping
    public List<RestauranteDto> listar() {
        return mapper.toCollectionDto(restauranteService.listar());
    }

    @GetMapping("/{id}")
    public RestauranteDto buscar(@PathVariable Long id) {
        return mapper.toDto(restauranteService.buscarOuFalhar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteDto adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restaurante = mapper.toDomainObject(restauranteInput);

            return mapper.toDto(restauranteService.salvar(restaurante));
        } catch (CozinhaNotFoundException | CidadeNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public RestauranteDto atualizar(@PathVariable Long id, @RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restauranteAtual = restauranteService.buscarOuFalhar(id);

            mapper.copyToDomainObject(restauranteInput, restauranteAtual);

            return mapper.toDto(restauranteService.salvar(restauranteAtual));
        } catch (CozinhaNotFoundException | CidadeNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long id) {
        restauranteService.ativar(id);
    }

    @DeleteMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long id) {
        restauranteService.inativar(id);
    }

    @PutMapping("/{id}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abrir(@PathVariable Long id) {
        restauranteService.abrir(id);
    }

    @PutMapping("/{id}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fechar(@PathVariable Long id) {
        restauranteService.fechar(id);
    }
}
