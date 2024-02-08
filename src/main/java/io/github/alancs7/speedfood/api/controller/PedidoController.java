package io.github.alancs7.speedfood.api.controller;

import io.github.alancs7.speedfood.api.mapper.PedidoMapper;
import io.github.alancs7.speedfood.api.mapper.PedidoResumoMapper;
import io.github.alancs7.speedfood.api.model.dto.PedidoDto;
import io.github.alancs7.speedfood.api.model.dto.PedidoResumoDto;
import io.github.alancs7.speedfood.api.model.input.PedidoInput;
import io.github.alancs7.speedfood.core.data.PageableTranslator;
import io.github.alancs7.speedfood.domain.exception.BusinessException;
import io.github.alancs7.speedfood.domain.exception.ResourceNotFoundException;
import io.github.alancs7.speedfood.domain.model.Pedido;
import io.github.alancs7.speedfood.domain.model.Usuario;
import io.github.alancs7.speedfood.domain.repository.filter.PedidoFilter;
import io.github.alancs7.speedfood.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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
    public Page<PedidoResumoDto> pesquisar(PedidoFilter filter, @PageableDefault(size = 10) Pageable pageable) {
        pageable = traduzirPageable(pageable);

        Page<Pedido> pedidos = pedidoService.listar(filter, pageable);

        List<PedidoResumoDto> pedidoResumoDtos = pedidoResumoMapper.toCollectionDto(pedidos.getContent());

        return new PageImpl<>(pedidoResumoDtos, pageable, pedidos.getTotalElements());
    }

    @GetMapping("/{codigoPedido}")
    public PedidoDto buscar(@PathVariable String codigoPedido) {
        return pedidoMapper.toDto(pedidoService.buscarOuFalhar(codigoPedido));
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

    private Pageable traduzirPageable(Pageable apiPageable) {
        var mapeamento = Map.of(
                "codigo", "codigo",
                "subtotal", "subtotal",
                "taxaFrete", "taxaFrete",
                "valorTotal", "valorTotal",
                "dataCriacao", "dataCriacao",
                "restaurante.nome", "restaurante.nome",
                "restaurante.id", "restaurante.id",
                "cliente.id", "cliente.id",
                "cliente.nome", "cliente.nome"
        );

        return PageableTranslator.translate(apiPageable, mapeamento);
    }
}
