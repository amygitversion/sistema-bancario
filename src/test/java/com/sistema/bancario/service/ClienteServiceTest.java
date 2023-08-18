package com.sistema.bancario.service;

import com.sistema.bancario.entity.Cliente;
import com.sistema.bancario.entity.Persona;
import com.sistema.bancario.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ClienteServiceImplTest {
    @Autowired
    private ClienteRepository clienteRepository;
    private Cliente clientePaul;
    private Cliente clienteMaria;

    @BeforeEach
    public void setup() {
        clientePaul = new Cliente(1L, "Paul", "M",
                23, "1715813655", "Ponciano",
                "123456", "111", true, new ArrayList<>());

        clienteMaria = new Cliente(2L, "Maria", "F",
                34, "1715813658", "Carcelen",
                "123457", "123", true, new ArrayList<>());
    }

    @Test
    void deberiaGuardarCliente() {
        Cliente clienteNuevo = clienteRepository.save(clientePaul);
        assertNotNull(clienteNuevo);
        assertEquals(clienteNuevo.getId(), 1L);
    }

    @Test
    void deberiaListarClientes() {
        clienteRepository.save(clientePaul);
        clienteRepository.save(clienteMaria);
        List<Persona> clientes = clienteRepository.findAll();
        assertNotNull(clientes);
    }
}