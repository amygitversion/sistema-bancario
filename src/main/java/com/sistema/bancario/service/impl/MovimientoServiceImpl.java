package com.sistema.bancario.service.impl;

import com.sistema.bancario.dto.MovimientoDto;
import com.sistema.bancario.entity.Movimiento;
import com.sistema.bancario.exception.NoExisteObjetoException;
import com.sistema.bancario.mapper.MovimientoMapper;
import com.sistema.bancario.repository.MovimientoRepository;
import com.sistema.bancario.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class MovimientoServiceImpl implements MovimientoService {

    private final  MovimientoRepository repository;
    private final MovimientoMapper movimientoMapper;
    @Autowired
    public MovimientoServiceImpl(MovimientoRepository repository,MovimientoMapper movimientoMapper) {
        this.repository = repository;
        this.movimientoMapper = movimientoMapper;
    }


    @Override
    @Transactional(readOnly = true)
    public List<MovimientoDto> listar() {
        List<Movimiento> movimientos = repository.findAll();
        return (movimientoMapper.entityToDto(movimientos));
    }

    @Override
    @Transactional(readOnly = true)
    public Movimiento buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(()->new NoExisteObjetoException(" el Movimiento con id: ",String.valueOf(id)));
    }

    @Override
    @Transactional
    public Movimiento guardar(Movimiento movimiento) {
        return repository.save(movimiento);
    }

    @Override
    @Transactional
    public Movimiento editar(Long id, Movimiento movimiento) {
        Movimiento movimientoEditado = buscarPorId(id);
        movimientoEditado.setValorMovimiento(movimiento.getValorMovimiento());
        movimientoEditado.setTipo(movimiento.getTipo());
        movimientoEditado.setFecha(movimiento.getFecha());
        movimientoEditado.setSaldoInicial(movimiento.getSaldoInicial());
        return movimientoEditado;
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Movimiento movimiento = buscarPorId(id);
        repository.deleteById(id);
    }
}
