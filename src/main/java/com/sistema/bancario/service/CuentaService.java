package com.sistema.bancario.service;

import com.sistema.bancario.dto.CuentaCompletoDto;
import com.sistema.bancario.dto.CuentaDto;
import com.sistema.bancario.entity.Cuenta;
import com.sistema.bancario.entity.Movimiento;
import com.sistema.bancario.exception.CuentaException;

import java.util.List;
import java.util.Optional;


public interface CuentaService {
    List<CuentaDto> listar();

    CuentaCompletoDto buscarPorId(Long id) throws CuentaException;

    Optional<Cuenta> buscarPorClienteIdYTipo(Long cliente_id, String tipo);

    Cuenta guardar(Cuenta cuenta);

    Cuenta editar(Long id, Cuenta cuenta);

    void eliminar(Long id);

    CuentaCompletoDto asignarMovimientoACuenta(Long cuentaId, Movimiento movimiento) ;

    Cuenta removerMovimientoDeCuenta(Long cuentaId, Long movimientoId);
}
