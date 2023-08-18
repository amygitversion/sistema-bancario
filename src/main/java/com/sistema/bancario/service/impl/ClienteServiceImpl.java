package com.sistema.bancario.service.impl;

import com.sistema.bancario.dto.*;
import com.sistema.bancario.entity.Cliente;
import com.sistema.bancario.entity.Cuenta;
import com.sistema.bancario.entity.Persona;
import com.sistema.bancario.exception.ClienteExisteException;
import com.sistema.bancario.exception.CuentaException;
import com.sistema.bancario.mapper.ClienteMapper;
import com.sistema.bancario.mapper.CuentaMapper;
import com.sistema.bancario.mapper.PersonMapper;
import com.sistema.bancario.repository.ClienteRepository;
import com.sistema.bancario.service.ClienteService;
import com.sistema.bancario.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final CuentaService cuentaService;
    private final PersonMapper personMapper;
    private final ClienteMapper clienteMapper;

    private final CuentaMapper cuentaMapper;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, CuentaService cuentaService, PersonMapper personMapper, ClienteMapper clienteMapper, CuentaMapper cuentaMapper) {
        this.clienteRepository = clienteRepository;
        this.cuentaService = cuentaService;
        this.personMapper = personMapper;
        this.clienteMapper = clienteMapper;
        this.cuentaMapper = cuentaMapper;

    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonaDto> listar() {
        return personMapper.entityToDto(clienteRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteDto buscarPorId(Long id) {
        Cliente cliente = (Cliente) clienteRepository.findById(id).get();
        return new ClienteDto(cliente.getContrasena(), cliente.getEstado(), personMapper.entityToDto((Persona) cliente));
    }


    private Optional<Persona> buscarPorIdentificacion(String identificacion) {
        return clienteRepository.findByIdentificacion(identificacion);
    }


    private Cliente guardar(Cliente Cliente) {
        return (Cliente) clienteRepository.save(Cliente);
    }

    @Override
    @Transactional
    public ClienteDto crear(ClienteDto clienteDto) throws ClienteExisteException {
        String identificacion = clienteDto.personaDto().identificacion();
        if (!identificacion.isEmpty() && buscarPorIdentificacion(identificacion).isPresent()) {
            throw new ClienteExisteException(identificacion);
        }
        Cliente clienteEntity = clienteMapper.dtoToEntity(clienteDto);
        Persona personaEntity = personMapper.dtoToEntity(clienteDto.personaDto());
        clienteEntity = guardar(new Cliente(personaEntity, clienteEntity));
        return new ClienteDto(clienteEntity.getContrasena(), clienteEntity.getEstado(), personMapper.entityToDto(clienteEntity));

    }

    public ClienteDto actualizarCliente(ClienteDto clienteDto, Long id) throws ClienteExisteException {
        Cliente clienteDB = (Cliente) clienteRepository.findById(id).get();
        if (!clienteDto.personaDto().identificacion().equalsIgnoreCase(clienteDB.getIdentificacion())
                && clienteRepository.findByIdentificacion(clienteDto.personaDto().identificacion()).isPresent()) {
            throw new ClienteExisteException(clienteDto.personaDto().identificacion());
        }
        Cliente cliente = new Cliente(personMapper.dtoToEntity(clienteDto.personaDto()), clienteMapper.dtoToEntity(clienteDto));
        Cliente clienteGuardadado = clienteRepository.save(editar(id, cliente));
        ClienteDto dto = new ClienteDto(clienteGuardadado.getContrasena(), clienteGuardadado.getEstado(), personMapper.entityToDto((Persona) clienteGuardadado));
        return dto;
    }


    private Cliente editar(Long id, Cliente cliente) {
        Cliente clienteEditado = (Cliente) clienteRepository.findById(id).get();
        clienteEditado.setEstado(cliente.getEstado());
        clienteEditado.setContrasena(cliente.getContrasena());
        clienteEditado.setEdad(cliente.getEdad());
        clienteEditado.setGenero(cliente.getGenero());
        clienteEditado.setDireccion(cliente.getDireccion());
        clienteEditado.setNombre(cliente.getNombre());
        clienteEditado.setTelefono(cliente.getTelefono());
        clienteEditado.setIdentificacion(cliente.getIdentificacion());
        return clienteEditado;
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }


    @Transactional
    public ClienteCompletoDto asignarCuentaACliente(Long clienteId, CuentaDto cuenta) throws CuentaException {
        Cliente cliente = (Cliente) clienteRepository.findById(clienteId).get();
        Optional<Cuenta> cuentaDB = cuentaService.buscarPorClienteIdYTipo(clienteId, cuenta.tipo());
        if (cuentaDB.isEmpty()) {
            cliente.agregarCuenta(cuentaMapper.dtoToEntity(cuenta));
            cliente = guardar(cliente);
            ClienteDto clienteDto = new ClienteDto(cliente.getContrasena(),cliente.getEstado(),personMapper.entityToDto((Persona) cliente));
            return new ClienteCompletoDto(clienteDto,cuentaMapper.entityToDto(cliente.getCuentas()));
        } else {
            throw new CuentaException(cuenta.tipo(), cliente.getNombre());
        }

    }

    @Override
    @Transactional
    public Cliente removerCuentaDeCliente(Long clienteId, Long cuentaId) throws CuentaException {
        try {
            Cliente cliente = (Cliente) clienteRepository.findById(clienteId).get();
            CuentaCompletoDto cuentaDto = cuentaService.buscarPorId(cuentaId);
            Cuenta cuenta = cuentaMapper.dtoToEntity(cuentaDto.cuentaDto());
            cliente.removerCuenta(cuenta);
            return guardar(cliente);
        } catch (CuentaException e) {
            throw new CuentaException(cuentaId.toString());
        }

    }

}
