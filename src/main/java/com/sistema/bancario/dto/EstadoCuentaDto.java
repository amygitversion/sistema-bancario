package com.sistema.bancario.dto;

import java.time.LocalDate;

public interface EstadoCuentaDto {
     LocalDate getFecha();

     String getNombreCliente();

     String getNumeroCuenta();

     String getTipoCuenta();

     Double getSaldoInicial();

     Boolean getEstadoCuenta();

     Double getMontoMovimiento();

     Double getSaldoDisponible();
}
