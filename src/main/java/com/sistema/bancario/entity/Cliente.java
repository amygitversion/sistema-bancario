package com.sistema.bancario.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Cliente extends Persona {
    @NotBlank
    private String contrasena;
    @NotNull
    private Boolean estado;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cliente_id")
    private List<Cuenta> cuentas = new ArrayList<>();


    public Cliente(Long id, @NotBlank String nombre, String genero, int edad, @NotBlank String identificacion, String direccion, String telefono, String contrasena, Boolean estado, List<Cuenta> cuentas) {
        super(id, nombre, genero, edad, identificacion, direccion, telefono);
        this.contrasena = contrasena;
        this.estado = estado;
        this.cuentas = cuentas;
    }

    public Cliente(Persona persona,Cliente cliente ){
        super(persona.getId(),persona.getNombre(),persona.getGenero(),persona.getEdad(),persona.getIdentificacion(), persona.getDireccion(), persona.getTelefono());
        this.contrasena =cliente.getContrasena();
        this.estado = cliente.getEstado();
        this.getCuentas();
    }

    public void agregarCuenta(Cuenta cuenta){
        cuentas.add(cuenta);
    }

    public void removerCuenta(Cuenta cuenta){
        cuentas.remove(cuenta);
    }
}
