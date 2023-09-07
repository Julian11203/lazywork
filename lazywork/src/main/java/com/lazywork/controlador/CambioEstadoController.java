package com.lazywork.controlador;


import com.lazywork.entidad.CambioEstado;
import com.lazywork.servicios.CambioEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cambio-estado")
public class CambioEstadoController {

    @Autowired
    private CambioEstadoService cambioEstadoService;

    @GetMapping
    public ResponseEntity<List<CambioEstado>> obtenerTodosLosCambioEstado() {
        List<CambioEstado> cambioEstado = cambioEstadoService.obtenerTodosLosCambioEstado();
        return ResponseEntity.ok(cambioEstado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CambioEstado> obtenerCambioEstadoPorId(@PathVariable String id) {
        Optional<CambioEstado> cambioEstado = cambioEstadoService.obtenerCambioEstadoPorId(id);
        if (cambioEstado.isPresent()) {
            return ResponseEntity.ok(cambioEstado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CambioEstado> crearCambioEstado(@RequestBody CambioEstado cambioEstado) {
        CambioEstado cambioEstadoCreado = cambioEstadoService.crearCambioEstado(cambioEstado);
        return ResponseEntity.status(HttpStatus.CREATED).body(cambioEstadoCreado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCambioEstado(@PathVariable String id) {
        cambioEstadoService.eliminarCambioEstado(id);
        return ResponseEntity.noContent().build();
    }
}
