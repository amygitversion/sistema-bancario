package com.sistema.bancario.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
//@Table(name = "cuenta", uniqueConstraints = {
//        @UniqueConstraint(name = "UK_TipoCuentaCliente", columnNames = {
//                "cliente_id", "tipo"
//        })
//})
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    private String numero;

    private String tipo;

    @NotNull
    private Double saldo;

    @NotNull
    private Boolean estado;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cuenta_id")
    private List<Movimiento> movimientos = new ArrayList<>();


//    @ManyToOne
//    @JoinColumn(name = "cliente_id")
//    private Cliente cliente;

    public void agregarMovimiento(Movimiento movimiento) {
        movimientos.add(movimiento);
    }

    public void removerMovimiento(Movimiento movimiento) {
        movimientos.remove(movimiento);
    }

}
