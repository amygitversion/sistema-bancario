package com.sistema.bancario.dto;

public record ClienteDto(String contrasena,
        Boolean estado, PersonaDto personaDto) {
}
