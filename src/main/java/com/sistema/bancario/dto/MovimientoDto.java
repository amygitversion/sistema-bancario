package com.sistema.bancario.dto;

import com.sistema.bancario.util.enums.TipoMovimiento;

import java.time.LocalDate;

public record MovimientoDto(Long id,
                            LocalDate fecha,
                            TipoMovimiento tipo,
                            Double valorMovimiento,
                            Double saldoInicial) {
}
