package com.sistema.bancario.mapper;

import com.sistema.bancario.dto.CuentaDto;
import com.sistema.bancario.entity.Cuenta;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CuentaMapper {
    CuentaDto entityToDto(Cuenta cuenta);
    Cuenta dtoToEntity(CuentaDto cuentaDto);
    List<CuentaDto> entityToDto(Iterable<Cuenta> cuentas);
}
