package io.github.alancs7.speedfood.api.controller;

import io.github.alancs7.speedfood.domain.filter.VendaDiariaFilter;
import io.github.alancs7.speedfood.domain.model.dto.VendaDiaria;
import io.github.alancs7.speedfood.domain.service.VendaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticasController {

    @Autowired
    private VendaQueryService vendaQueryService;

    @GetMapping("/vendas-diarias")
    private List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter) {
        return vendaQueryService.consultarVendasDiarias(filter);
    }

}
