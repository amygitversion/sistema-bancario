package com.sistema.bancario.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sistema.bancario.util.enums.TipoMovimiento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
     @JsonFormat(pattern="dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoMovimiento tipo;

    @NotNull
    private Double valorMovimiento;

    @NotNull
    private Double saldoInicial;
}
