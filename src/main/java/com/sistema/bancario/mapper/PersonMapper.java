package com.sistema.bancario.mapper;
import com.sistema.bancario.dto.PersonaDto;
import com.sistema.bancario.entity.Persona;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonaDto entityToDto(Persona client);
    List<PersonaDto> entityToDto(Iterable<Persona> clients);
    Persona dtoToEntity(PersonaDto clienteDto);
}