package io.github.alancs7.speedfood.api.controller;

import io.github.alancs7.speedfood.api.mapper.PedidoMapper;
import io.github.alancs7.speedfood.api.mapper.PedidoResumoMapper;
import io.github.alancs7.speedfood.api.model.dto.PedidoDto;
import io.github.alancs7.speedfood.api.model.dto.PedidoResumoDto;
import io.github.alancs7.speedfood.api.model.input.PedidoInput;
import io.github.alancs7.speedfood.domain.exception.BusinessException;
import io.github.alancs7.speedfood.domain.exception.ResourceNotFoundException;
import io.github.alancs7.speedfood.domain.model.Pedido;
import io.github.alancs7.speedfood.domain.model.Usuario;
import io.github.alancs7.speedfood.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDto adicionar(@RequestBody @Valid PedidoInput pedidoInput) {
        try {
            Pedido novoPedido = pedidoMapper.toDomainObject(pedidoInput);

            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

            return pedidoMapper.toDto(pedidoService.emitir(novoPedido));
        } catch (ResourceNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}
