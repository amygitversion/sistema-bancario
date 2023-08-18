package com.sistema.bancario.dto;

public record PersonaDto(Long id,
                         String nombre,
                         String genero,
                         int edad,
                         String identificacion,
                         String direccion,
                         String telefono) {
}
