package io.github.alancs7.speedfood.api.controller;

import io.github.alancs7.speedfood.api.mapper.PedidoMapper;
import io.github.alancs7.speedfood.api.mapper.PedidoResumoMapper;
import io.github.alancs7.speedfood.api.model.dto.PedidoDto;
import io.github.alancs7.speedfood.api.model.dto.PedidoResumoDto;
import io.github.alancs7.speedfood.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoMapper pedidoMapper;

    @Autowired
    private PedidoResumoMapper pedidoResumoMapper;

    @GetMapping
    public List<PedidoResumoDto> listar() {
        return pedidoResumoMapper.toCollectionDto(pedidoService.listar());
    }

    @GetMapping("/{id}")
    public PedidoDto buscar(@PathVariable Long id) {
        return pedidoMapper.toDto(pedidoService.buscarOuFalhar(id));
    }
}
