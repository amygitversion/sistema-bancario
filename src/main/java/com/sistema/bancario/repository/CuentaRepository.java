package com.sistema.bancario.repository;

import com.sistema.bancario.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    Optional<Cuenta> findByNumero(String numero);


    @Query(value = "SELECT * FROM CUENTA WHERE CLIENTE_ID = ?1 AND TIPO = ?2", nativeQuery = true)
    Optional<Cuenta> findByClienteIdAndTipo(Long cliente_id, String tipo);
}
