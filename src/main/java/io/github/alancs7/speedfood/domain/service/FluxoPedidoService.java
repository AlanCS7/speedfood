package io.github.alancs7.speedfood.domain.service;

import io.github.alancs7.speedfood.domain.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.github.alancs7.speedfood.domain.service.EnvioEmailService.Mensagem;


@Service
public class FluxoPedidoService {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private EnvioEmailService envioEmailService;

    @Transactional
    public void confirmar(String codigo) {
        Pedido pedido = pedidoService.buscarOuFalhar(codigo);
        pedido.confirmar();

        var mensagem = Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
                .corpo("O pedido de codigo <strong>" + pedido.getCodigo() + "</strong> foi confirmado!")
                .destinatario(pedido.getCliente().getEmail())
                .build();

        envioEmailService.enviar(mensagem);
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
