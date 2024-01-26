package io.github.alancs7.speedfood.domain.service;

import io.github.alancs7.speedfood.domain.exception.FormaPagamentoNotFoundException;
import io.github.alancs7.speedfood.domain.exception.ResourceInUseException;
import io.github.alancs7.speedfood.domain.model.FormaPagamento;
import io.github.alancs7.speedfood.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FormaPagamentoService {

    public static final String MSG_FORMA_PAGAMENTO_EM_USO = "Forma de pagamento de código %d não pode ser removida, pois está em uso";

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public List<FormaPagamento> listar() {
        return formaPagamentoRepository.findAll();
    }

    public FormaPagamento buscarOuFalhar(Long id) {
        return formaPagamentoRepository.findById(id)
                .orElseThrow(() -> new FormaPagamentoNotFoundException(id));
    }

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return formaPagamentoRepository.save(formaPagamento);
    }

    @Transactional
    public void excluir(Long id) {
        try {
            formaPagamentoRepository.deleteById(id);
            formaPagamentoRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new FormaPagamentoNotFoundException(id);

        } catch (DataIntegrityViolationException e) {
            throw new ResourceInUseException(
                    String.format(MSG_FORMA_PAGAMENTO_EM_USO, id));
        }
    }
}
