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
    public void confirmar(String codigo) {
        Pedido pedido = pedidoService.buscarOuFalhar(codigo);
        pedido.confirmar();
    }

    @Transactional
    public void entregar(String codigo) {
        Pedido pedido = pedidoService.buscarOuFalhar(codigo);
        pedido.entregar();
    }

    @Transactional
    public void cancelar(String codigo) {
        Pedido pedido = pedidoService.buscarOuFalhar(codigo);
        pedido.cancelar();
    }
}
