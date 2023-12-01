package io.github.alancs7.speedfood.domain.repository;

import io.github.alancs7.speedfood.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

}