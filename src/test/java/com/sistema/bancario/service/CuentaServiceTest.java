package com.sistema.bancario.service;

import com.sistema.bancario.entity.Cuenta;
import com.sistema.bancario.repository.CuentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@DataJpaTest
class CuentaServiceTest {
    @Autowired
    private CuentaRepository cuentaRepository;

    private Cuenta cuenta1;
    private Cuenta cuenta2;

    @BeforeEach
    public void setup() {
        cuenta1 = new Cuenta();
        cuenta1.setSaldo(10.0);
        cuenta1.setEstado(true);
        cuenta1.setMovimientos(new ArrayList<>());
        cuenta1.setId(1L);
        cuenta1.setTipo("AHORROS");
        cuenta1.setNumero("1234");

        cuenta2 = new Cuenta();
        cuenta2.setSaldo(20.0);
        cuenta2.setEstado(true);
        cuenta2.setMovimientos(new ArrayList<>());
        cuenta2.setId(2L);
        cuenta2.setTipo("CORRIENTE");
        cuenta2.setNumero("4556");
    }

    @Test
    void deberiaGuardarCuenta() {
        Cuenta cuentaNueva = cuentaRepository.save(cuenta1);
        assertNotNull(cuentaNueva);
        assertEquals(cuentaNueva.getId(), 1L);
    }

    @Test
    void deberiaListarCuentas() {
        cuentaRepository.save(cuenta1);
        cuentaRepository.save(cuenta2);
        List<Cuenta> cuentas = cuentaRepository.findAll();
        assertNotNull(cuentas);
    }


}