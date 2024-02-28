package io.github.alancs7.speedfood.api.controller;

import io.github.alancs7.speedfood.api.mapper.FormaPagamentoMapper;
import io.github.alancs7.speedfood.api.model.dto.FormaPagamentoDto;
import io.github.alancs7.speedfood.api.model.input.FormaPagamentoInput;
import io.github.alancs7.speedfood.domain.model.FormaPagamento;
import io.github.alancs7.speedfood.domain.service.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private FormaPagamentoMapper mapper;

    @GetMapping
    public ResponseEntity<List<FormaPagamentoDto>> listar() {
        List<FormaPagamentoDto> formaPagamentosDto = mapper.toCollectionDto(formaPagamentoService.listar());

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(formaPagamentosDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamentoDto> buscar(@PathVariable Long id) {
        FormaPagamentoDto formaPagamentoDto = mapper.toDto(formaPagamentoService.buscarOuFalhar(id));

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(formaPagamentoDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoDto adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento = mapper.toDomainObject(formaPagamentoInput);

        return mapper.toDto(formaPagamentoService.salvar(formaPagamento));
    }

    @PutMapping("/{id}")
    public FormaPagamentoDto atualizar(@PathVariable Long id, @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(id);

        mapper.copyToDomainObject(formaPagamentoInput, formaPagamento);

        return mapper.toDto(formaPagamentoService.salvar(formaPagamento));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        formaPagamentoService.excluir(id);
    }
}
