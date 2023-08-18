package com.sistema.bancario.mapper;

import com.sistema.bancario.dto.MovimientoDto;
import com.sistema.bancario.entity.Movimiento;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {
    MovimientoDto entityToDto(Movimiento movimiento);
    Movimiento dtoToEntity(MovimientoDto movimientoDto);
    List<MovimientoDto> entityToDto(Iterable<Movimiento> movimientos);
}
