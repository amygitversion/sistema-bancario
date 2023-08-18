package com.sistema.bancario.service;

import com.sistema.bancario.dto.MovimientoDto;
import com.sistema.bancario.entity.Movimiento;

import java.util.List;


public interface MovimientoService {
    List<MovimientoDto> listar();

    Movimiento buscarPorId(Long id);

    Movimiento guardar(Movimiento cuenta);

    Movimiento editar(Long id, Movimiento movimiento);

    void eliminar(Long id);
}
