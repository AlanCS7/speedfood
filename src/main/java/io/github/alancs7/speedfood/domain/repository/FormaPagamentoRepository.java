package io.github.alancs7.speedfood.domain.repository;

import io.github.alancs7.speedfood.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

    @Query("SELECT MAX(dataAtualizacao) FROM FormaPagamento")
    OffsetDateTime getDataUltimaAtualizacao();

    @Query("SELECT MAX(dataAtualizacao) FROM FormaPagamento WHERE id = :formaPagamentoId")
    OffsetDateTime getDataUltimaAtualizacaoById(Long formaPagamentoId);
}