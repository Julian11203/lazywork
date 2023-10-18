package com.lazywork.estructuraCERS.controlador;

import com.lazywork.estructuraCERS.entidad.CambioEstado;
import com.lazywork.estructuraCERS.servicios.CambioEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/cambioestado")
public class CambioEstadoController {

    @Autowired
    private CambioEstadoService cambioEstadoService;

    @GetMapping("/listar")
    public ResponseEntity<List<CambioEstado>> obtenerTodasLosCambioEstado() {
        List<CambioEstado> cambioEstado = cambioEstadoService.obtenerTodosLosCambioEstado();
        return ResponseEntity.ok(cambioEstado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CambioEstado> obtenerCambioEstadoPorId(@PathVariable Long id) {
        Optional<CambioEstado> cambioEstado = cambioEstadoService.obtenerCambioEstadoPorId(id);
        if (cambioEstado.isPresent()) {
            return ResponseEntity.ok(cambioEstado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<CambioEstado> crearCambioEstado(@RequestBody CambioEstado cambioEstadoB) {
        cambioEstadoService.crearCambioEstado(cambioEstadoB);
        return ResponseEntity.ok(cambioEstadoB);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<CambioEstado> actualizarCambioEstado(@PathVariable Long id, @RequestBody CambioEstado cambioEstadoB) {
        Optional<CambioEstado> cambioEstado = cambioEstadoService.obtenerCambioEstadoPorId(cambioEstadoB.getCambioEstadoID());
        if (cambioEstado.isPresent()) {
            cambioEstadoService.crearCambioEstado( cambioEstadoB);
            return ResponseEntity.ok(cambioEstado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarCambioEstado(@PathVariable Long id) {
        cambioEstadoService.eliminarCambioEstado(id);
        return ResponseEntity.noContent().build();
    }
}