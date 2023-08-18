package com.sistema.bancario.dto;

public record CuentaDto(Long id,
                        String numero,
                        String tipo,
                        Double saldo,
                        Boolean estado) {
}
