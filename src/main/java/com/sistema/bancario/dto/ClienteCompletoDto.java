package com.sistema.bancario.dto;

import java.util.List;

public record ClienteCompletoDto(ClienteDto clienteDto, List<CuentaDto> cuentaDtos) {
}
