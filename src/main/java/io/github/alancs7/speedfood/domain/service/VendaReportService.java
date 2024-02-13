package io.github.alancs7.speedfood.domain.service;

import io.github.alancs7.speedfood.domain.filter.VendaDiariaFilter;

public interface VendaReportService {

    byte[] emitirVendasDiarias(VendaDiariaFilter filter, String timeOffset);
}
