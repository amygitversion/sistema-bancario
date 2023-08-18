package com.sistema.bancario.repository;

import com.sistema.bancario.dto.EstadoCuentaDto;
import com.sistema.bancario.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface EstadoCuentaRepository extends JpaRepository<Movimiento,Long> {
    @Query(value = "SELECT m.fecha as fecha, p.nombre as nombreCliente," +
            " cu.numero as numeroCuenta, cu.tipo as tipoCuenta, m.saldo_inicial as saldoInicial," +
            " cu.estado as estadoCuenta,m.valor_movimiento as montoMovimiento, cu.saldo as saldoDisponible" +
            " from cliente cl, cuenta cu, movimiento m, persona p" +
            " where cl.id=cu.cliente_id" +
            " and cu.id=m.cuenta_id" +
            " and p.id= cl.id" +
            " and m.fecha between ?1 and ?2" +
            " and cl.id=?3",nativeQuery = true)
    List<EstadoCuentaDto> listaMovimientosPorFechaCliente(LocalDate fechaInicio, LocalDate fechaFin, Long clienteId);

}
