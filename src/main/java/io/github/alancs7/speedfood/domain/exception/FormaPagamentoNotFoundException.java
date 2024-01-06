package io.github.alancs7.speedfood.domain.exception;

public class FormaPagamentoNotFoundException extends ResourceNotFoundException {

    private static final long serialVersionUID = 1L;

    public FormaPagamentoNotFoundException(String message) {
        super(message);
    }

    public FormaPagamentoNotFoundException(Long id) {
        this(String.format("Não existe um cadastro de forma de pagamento com código %d", id));
    }
}
