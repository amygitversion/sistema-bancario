package com.sistema.bancario.service.impl;

import com.sistema.bancario.dto.CuentaCompletoDto;
import com.sistema.bancario.dto.CuentaDto;
import com.sistema.bancario.entity.Cuenta;
import com.sistema.bancario.entity.Movimiento;
import com.sistema.bancario.exception.CuentaException;
import com.sistema.bancario.exception.FondosInsuficientesException;
import com.sistema.bancario.mapper.CuentaMapper;
import com.sistema.bancario.mapper.MovimientoMapper;
import com.sistema.bancario.repository.CuentaRepository;
import com.sistema.bancario.service.CuentaService;
import com.sistema.bancario.service.MovimientoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaService {
 Logger logger=  LoggerFactory.getLogger(CuentaServiceImpl.class);

    private final CuentaRepository cuentaRepository;
    private final MovimientoService movimientoService;
    private final CuentaMapper cuentaMapper;
    private final MovimientoMapper movimientoMapper;

    @Autowired
    public CuentaServiceImpl(CuentaRepository cuentaRepository, MovimientoService movimientoService,CuentaMapper cuentaMapper,MovimientoMapper movimientoMapper) {
        this.cuentaRepository = cuentaRepository;
        this.movimientoService = movimientoService;
        this.cuentaMapper = cuentaMapper;
        this.movimientoMapper = movimientoMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CuentaDto> listar() {
        logger.info("Entra al Servicio de listar");
        List<Cuenta>cuentas = cuentaRepository.findAll();
        return cuentaMapper.entityToDto(cuentas);
    }

    @Override
    @Transactional(readOnly = true)
    public CuentaCompletoDto buscarPorId(Long id) throws CuentaException {
        Optional<Cuenta> cuenta =cuentaRepository.findById(id);
        if(cuenta.isPresent()) {

            return new CuentaCompletoDto(cuentaMapper.entityToDto(cuenta.get()),movimientoMapper.entityToDto(cuenta.get().getMovimientos()));
        }
        throw new CuentaException(id.toString());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cuenta> buscarPorClienteIdYTipo(Long cliente_id, String tipo) {
        return cuentaRepository.findByClienteIdAndTipo(cliente_id, tipo);
    }


    @Override
    @Transactional
    public Cuenta guardar(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }


    @Override
    @Transactional
    public Cuenta editar(Long id, Cuenta cuenta) {
        Cuenta cuentaEditada = cuentaRepository.findById(id).get();
        cuentaEditada.setEstado(cuenta.getEstado());
        cuentaEditada.setTipo(cuenta.getTipo());
        cuentaEditada.setSaldo(cuenta.getSaldo());
        cuentaEditada.setMovimientos(cuenta.getMovimientos());
        return cuentaEditada;
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
       // Cuenta cuenta = cuentaRepository.findById(id).get();
        cuentaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CuentaCompletoDto asignarMovimientoACuenta(Long cuentaId, Movimiento movimiento) {
        Cuenta cuenta = cuentaRepository.findById(cuentaId).get();
        Double saldoFinal = cuenta.getSaldo() + movimiento.getValorMovimiento();
        if (saldoFinal < 0) {
            logger.error("Saldo Insuiciente " + saldoFinal);
            throw new FondosInsuficientesException(movimiento.getValorMovimiento(), cuenta.getSaldo());
        }
        cuenta.setSaldo(saldoFinal);
        cuenta.agregarMovimiento(movimiento);
        cuenta= guardar(cuenta);
        return new CuentaCompletoDto(cuentaMapper.entityToDto(cuenta),movimientoMapper.entityToDto(cuenta.getMovimientos()));
    }

    @Override
    @Transactional
    public Cuenta removerMovimientoDeCuenta(Long cuentaId, Long movimientoId) {
        Cuenta cuenta = cuentaRepository.findById(cuentaId).get();
        Movimiento movimiento = movimientoService.buscarPorId(movimientoId);
        cuenta.removerMovimiento(movimiento);
        return guardar(cuenta);
    }
}
