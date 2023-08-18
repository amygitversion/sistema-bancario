package com.sistema.bancario.service.impl;

import com.sistema.bancario.dto.EstadoCuentaDto;
import com.sistema.bancario.repository.EstadoCuentaRepository;
import com.sistema.bancario.service.EstadoCuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EstadoCuentaServiceImpl implements EstadoCuentaService {
    private final EstadoCuentaRepository estadoCuentaRepository;

    @Autowired
    public EstadoCuentaServiceImpl(EstadoCuentaRepository estadoCuentaRepository) {
        this.estadoCuentaRepository = estadoCuentaRepository;
    }

    @Override
    public List<EstadoCuentaDto> listaMovimientosPorFechaCliente(String fechaInicio, String fechaFin, String clienteId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaInicial = LocalDate.parse(fechaInicio, formatter);
        LocalDate fechaFinal = LocalDate.parse(fechaFin, formatter);
        Long id = Long.valueOf(clienteId);

        return estadoCuentaRepository.listaMovimientosPorFechaCliente(fechaInicial, fechaFinal, id);
    }
}
