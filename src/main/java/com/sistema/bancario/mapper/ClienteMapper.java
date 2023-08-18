package com.sistema.bancario.mapper;

import com.sistema.bancario.dto.ClienteDto;
import com.sistema.bancario.entity.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteDto entityToDto(Cliente client);
    Cliente dtoToEntity(ClienteDto clienteDto);
}
