package io.github.alancs7.speedfood.infrastructure.repository.spec;

import io.github.alancs7.speedfood.domain.model.Pedido;
import io.github.alancs7.speedfood.domain.repository.filter.PedidoFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class PedidoSpecs {

    public static Specification<Pedido> usandoFiltro(PedidoFilter filter) {
        return (root, query, builder) -> {
            root.fetch("cliente");
            root.fetch("restaurante").fetch("cozinha");

            var predicates = new ArrayList<Predicate>();

            if (filter.getClienteId() != null) {
                predicates.add(builder.equal(root.get("cliente"), filter.getClienteId()));
            }

            if (filter.getRestauranteId() != null) {
                predicates.add(builder.equal(root.get("restaurante"), filter.getRestauranteId()));
            }

            if (filter.getDataCriacaoInicio() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filter.getDataCriacaoInicio()));
            }

            if (filter.getDataCriacaoFim() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filter.getDataCriacaoFim()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
