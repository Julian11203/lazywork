package com.example.demo.Controlador;

import com.example.demo.Entidad.E_CambioEstado;
import com.example.demo.Servicio.S_CambioEstado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/cambioestado")
public class C_CambioEstado {

    @Autowired
    private S_CambioEstado cambioEstadoService;

    @GetMapping("/listar")
    public ResponseEntity<List<E_CambioEstado>> obtenerTodasLosCambioEstado() {
        List<E_CambioEstado> cambioEstado = cambioEstadoService.obtenerTodosLosCambioEstado();
        return ResponseEntity.ok(cambioEstado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<E_CambioEstado> obtenerCambioEstadoPorId(@PathVariable Long id) {
        Optional<E_CambioEstado> cambioEstado = cambioEstadoService.obtenerCambioEstadoPorId(id);
        if (cambioEstado.isPresent()) {
            return ResponseEntity.ok(cambioEstado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<E_CambioEstado> crearCambioEstado(@RequestBody E_CambioEstado cambioEstadoB) {
        cambioEstadoService.crearCambioEstado(cambioEstadoB);
        return ResponseEntity.ok(cambioEstadoB);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<E_CambioEstado> actualizarCambioEstado(@PathVariable Long id, @RequestBody E_CambioEstado cambioEstadoB) {
        Optional<E_CambioEstado> cambioEstado = cambioEstadoService.obtenerCambioEstadoPorId(cambioEstadoB.getCambioEstadoID());
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