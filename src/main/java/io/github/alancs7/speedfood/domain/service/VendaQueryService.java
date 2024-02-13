package io.github.alancs7.speedfood.domain.service;

import io.github.alancs7.speedfood.domain.filter.VendaDiariaFilter;
import io.github.alancs7.speedfood.domain.model.dto.VendaDiaria;

import java.util.List;

public interface VendaQueryService {

    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter, String timeOffset);
}
