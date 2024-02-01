package io.github.alancs7.speedfood.domain.service;

import io.github.alancs7.speedfood.domain.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class FluxoPedidoService {

    @Autowired
    private PedidoService pedidoService;

    @Transactional
    public void confirmar(Long id) {
        Pedido pedido = pedidoService.buscarOuFalhar(id);
        pedido.confirmar();
    }

    @Transactional
    public void entregar(Long pedidoId) {
        Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);
        pedido.entregar();
    }

    @Transactional
    public void cancelar(Long pedidoId) {
        Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);
        pedido.cancelar();
    }
}
