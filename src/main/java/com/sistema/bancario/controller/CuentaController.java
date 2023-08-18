package com.sistema.bancario.controller;

import com.sistema.bancario.entity.Movimiento;
import com.sistema.bancario.exception.CuentaException;
import com.sistema.bancario.service.ClienteService;
import com.sistema.bancario.service.CuentaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    Logger logger = LoggerFactory.getLogger((CuentaController.class));
    private final CuentaService cuentaService;

    @Autowired
    public CuentaController(CuentaService cuentaService, ClienteService clienteService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public ResponseEntity<?>Llistar() {
        return ResponseEntity.status(HttpStatus.OK).body(cuentaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalleCuenta(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cuentaService.buscarPorId(id));
        } catch (CuentaException e) {
            logger.error("Error al obtener el detalle de la cuenta "+ e.getMessage());
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        cuentaService.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente la cuenta con id " +id );
    }

    @PostMapping("/asignar-movimiento/{cuentaId}")
    public ResponseEntity<?> asignarMovimiento(@RequestBody Movimiento movimiento, @PathVariable Long cuentaId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cuentaService.asignarMovimientoACuenta(cuentaId, movimiento));
    }

    @PutMapping("/remover-movimiento/{cuentaId}/{movimientoId}")
    public ResponseEntity<?> removerMovimiento(@PathVariable Long cuentaId, @PathVariable Long movimientoId) {
        cuentaService.removerMovimientoDeCuenta(cuentaId, movimientoId);
        return ResponseEntity.noContent().build();
    }

}
