package com.sistema.bancario.repository;

import com.sistema.bancario.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PersonaRepository extends JpaRepository <Persona, Long>{
    Optional<Persona> findByIdentificacion(String identificacion);
}
