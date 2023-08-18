package com.sistema.bancario.dto;

import java.util.List;

public record CuentaCompletoDto(CuentaDto cuentaDto, List<MovimientoDto> movimientoDtoList) {
}
