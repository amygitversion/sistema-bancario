package com.sistema.bancario.controller;

import com.sistema.bancario.entity.Movimiento;
import com.sistema.bancario.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;

    @Autowired
    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.status(HttpStatus.OK).body( movimientoService.listar());

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalleMovimiento(@PathVariable Long id) {
        Movimiento movimiento = movimientoService.buscarPorId(id);
        return ResponseEntity.ok(movimiento);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Movimiento movimiento = movimientoService.buscarPorId(id);
        movimientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
