package com.sistema.bancario.service;

import com.sistema.bancario.entity.Movimiento;
import com.sistema.bancario.repository.MovimientoRepository;
import com.sistema.bancario.util.enums.TipoMovimiento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@DataJpaTest
class MovimientoServiceTest {

    @Autowired
    private MovimientoRepository movimientoRepository;
    private Movimiento movimientoCRE;
    private Movimiento movimientoDEB;

    @BeforeEach
    public void setup() {
        movimientoCRE = new Movimiento();
        movimientoCRE.setId(1L);
        movimientoCRE.setFecha(LocalDate.of(2023, 4, 19));
        movimientoCRE.setTipo(TipoMovimiento.CRE);
        movimientoCRE.setValorMovimiento(5.5);
        movimientoCRE.setSaldoInicial(10.0);

        movimientoDEB= new Movimiento();
        movimientoDEB.setId(2L);
        movimientoDEB.setFecha(LocalDate.of(2023, 6, 27));
        movimientoDEB.setTipo(TipoMovimiento.DEB);
        movimientoDEB.setValorMovimiento(23.5);
        movimientoDEB.setSaldoInicial(50.0);
    }

    @Test
    void deberiaGuardarMovimiento() {
        Movimiento movimientoNuevo = movimientoRepository.save(movimientoCRE);
        assertNotNull(movimientoNuevo);
        assertEquals(movimientoNuevo.getId(), 1L);
    }

    @Test
    void deberiaListarMovimientos() {
        movimientoRepository.save(movimientoCRE);
        movimientoRepository.save(movimientoDEB);
        List<Movimiento> movimientos = movimientoRepository.findAll();
        assertNotNull(movimientos);
    }
}