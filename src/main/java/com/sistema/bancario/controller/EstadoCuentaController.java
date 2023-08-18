package com.sistema.bancario.controller;

import com.sistema.bancario.dto.EstadoCuentaDto;
import com.sistema.bancario.service.EstadoCuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class EstadoCuentaController {

    private final EstadoCuentaService estadoCuentaDtoService;

    @Autowired
    public EstadoCuentaController(EstadoCuentaService estadoCuentaDtoService) {
        this.estadoCuentaDtoService = estadoCuentaDtoService;
    }

    @GetMapping
    public ResponseEntity<?> listarMovimientosPorFecha(@RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam String clienteId) {
        List<EstadoCuentaDto> movimientosPorFechas = estadoCuentaDtoService.listaMovimientosPorFechaCliente(fechaInicio, fechaFin, clienteId);
        ResponseEntity.badRequest()
                .body(Collections
                        .singletonMap("Error Fecha Formato:dd/MM/yyyy", fechaInicio));

        return ResponseEntity.ok(movimientosPorFechas);
    }
}
