package com.sistema.bancario.service;

import com.sistema.bancario.dto.ClienteCompletoDto;
import com.sistema.bancario.dto.ClienteDto;
import com.sistema.bancario.dto.CuentaDto;
import com.sistema.bancario.dto.PersonaDto;
import com.sistema.bancario.entity.Cliente;
import com.sistema.bancario.exception.ClienteExisteException;
import com.sistema.bancario.exception.CuentaException;

import java.util.List;


public interface ClienteService {

    void eliminar(Long id);
    ClienteDto crear(ClienteDto clienteDto) throws ClienteExisteException;
    List<PersonaDto> listar();
    ClienteDto buscarPorId(Long id);
    ClienteDto actualizarCliente(ClienteDto clienteDto,Long id) throws ClienteExisteException;
    ClienteCompletoDto asignarCuentaACliente(Long clienteId, CuentaDto cuenta) throws CuentaException;
    Cliente removerCuentaDeCliente(Long clienteId, Long cuentaId) throws CuentaException;
}
