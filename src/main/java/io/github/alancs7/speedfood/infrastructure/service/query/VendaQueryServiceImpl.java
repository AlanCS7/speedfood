package io.github.alancs7.speedfood.infrastructure.service.query;

import io.github.alancs7.speedfood.domain.filter.VendaDiariaFilter;
import io.github.alancs7.speedfood.domain.model.Pedido;
import io.github.alancs7.speedfood.domain.model.StatusPedido;
import io.github.alancs7.speedfood.domain.model.dto.VendaDiaria;
import io.github.alancs7.speedfood.domain.service.VendaQueryService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter, String timeOffset) {
        var builder = entityManager.getCriteriaBuilder();
        var query = builder.createQuery(VendaDiaria.class);
        var root = query.from(Pedido.class);
        var predicates = new ArrayList<Predicate>();

        var functionConvertTz = builder.function(
                "convert_tz", Date.class, root.get("dataCriacao"),
                builder.literal("+00:00"), builder.literal(timeOffset));

        var functionDate = builder.function("date", Date.class, functionConvertTz);

        var selection = builder.construct(VendaDiaria.class,
                functionDate,
                builder.count(root.get("id")),
                builder.sum(root.get("valorTotal")));

        if (filter.getRestauranteId() != null) {
            predicates.add(builder.equal(root.get("restaurante"), filter.getRestauranteId()));
        }

        if (filter.getDataCriacaoInicio() != null) {
            predicates.add((builder.greaterThanOrEqualTo(root.get("dataCriacao"), filter.getDataCriacaoInicio())));
        }

        if (filter.getDataCriacaoFim() != null) {
            predicates.add((builder.greaterThanOrEqualTo(root.get("dataCriacao"), filter.getDataCriacaoFim())));
        }

        predicates.add(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));

        query.select(selection);
        query.where(predicates.toArray(new Predicate[0]));
        query.groupBy(functionDate);

        return entityManager.createQuery(query).getResultList();
    }
}
