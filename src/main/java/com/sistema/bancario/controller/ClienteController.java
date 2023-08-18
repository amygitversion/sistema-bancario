package com.sistema.bancario.controller;

import com.sistema.bancario.dto.ClienteCompletoDto;
import com.sistema.bancario.dto.ClienteDto;
import com.sistema.bancario.dto.CuentaDto;
import com.sistema.bancario.dto.PersonaDto;
import com.sistema.bancario.exception.ClienteExisteException;
import com.sistema.bancario.exception.CuentaException;
import com.sistema.bancario.service.ClienteService;
import com.sistema.bancario.util.enums.Validacion;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<PersonaDto> listar() {
        return clienteService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalleCliente(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody ClienteDto cliente, BindingResult result) {

        if (result.hasErrors()) {
            return Validacion.erroresRequest(result);
        }
        try {
            ClienteDto clienteDto = clienteService.crear(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteDto);
        } catch (ClienteExisteException e) {
            logger.error("No se pudo crear el Cliente " +e.getMessage());
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Se eliminó con éxito el cliente con id "+ id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody ClienteDto clienteDto, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return Validacion.erroresRequest(result);
        }
        try {
            clienteDto = clienteService.actualizarCliente(clienteDto,id);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteDto);
        } catch (ClienteExisteException e) {
            logger.error("No se pudo crear el Cliente "+e.getMessage());
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }

    }


    @PostMapping("/asignar-cuenta/{clienteId}")
    public ResponseEntity<?> asignarCuenta(@RequestBody CuentaDto cuentaDto, @PathVariable Long clienteId) {

        try {
            ClienteCompletoDto clienteCompletoDto = clienteService.asignarCuentaACliente(clienteId, cuentaDto);
            if(clienteCompletoDto !=null){
                return ResponseEntity.status(HttpStatus.CREATED).body(clienteCompletoDto);
            }else {
                return ResponseEntity.notFound().build();
            }
        } catch (CuentaException e) {
            logger.error("No se pudo asignar la cuenta "+e.getMessage());
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/remover-cuenta/{clienteId}/{cuentaId}")
    public ResponseEntity<?> removerCuenta(@PathVariable Long clienteId,@PathVariable Long cuentaId) {
        try {
            clienteService.removerCuentaDeCliente(clienteId, cuentaId);
            return ResponseEntity.status(HttpStatus.OK).body("Se eliminó de forma correcta");
        } catch (CuentaException e) {
            logger.error("No se pudo eliminar la cuenta "+e.getMessage());
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }

    }

}
