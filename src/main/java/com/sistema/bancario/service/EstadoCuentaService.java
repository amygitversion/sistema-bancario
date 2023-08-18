package com.sistema.bancario.service;

import com.sistema.bancario.dto.EstadoCuentaDto;

import java.util.List;

public interface EstadoCuentaService {

    List<EstadoCuentaDto> listaMovimientosPorFechaCliente(String fechaInicio, String fechaFin, String clienteId);
}
