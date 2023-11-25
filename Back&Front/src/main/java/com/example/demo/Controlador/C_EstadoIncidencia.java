package com.example.demo.Controlador;

import com.example.demo.Entidad.E_EstadoIncidencia;
import com.example.demo.Servicio.S_EstadosIncidencias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/estados")

public class C_EstadoIncidencia {

    @Autowired
    S_EstadosIncidencias estadosIncidenciasService;

    @GetMapping("/listar")
    public ResponseEntity<List<E_EstadoIncidencia>> obtenerTodasLosEstadosIncidencia() {
        List<E_EstadoIncidencia> estadoIncidencias = estadosIncidenciasService.obtenerTodosLosEstadosIncidencias();
        return ResponseEntity.ok(estadoIncidencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<E_EstadoIncidencia> obtenerEstadosIncidenciasPorId(@PathVariable Long id) {
        Optional<E_EstadoIncidencia> estadoIncidencia =estadosIncidenciasService.obtenerEstadosIncidenciasPorId(id);
        if (estadoIncidencia.isPresent()) {
            return ResponseEntity.ok(estadoIncidencia.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping ("/crear")
    public ResponseEntity<E_EstadoIncidencia> crearEstadosIncidencias(@RequestBody E_EstadoIncidencia estadoIncidencia) {
        E_EstadoIncidencia estadosIncidenciasCreada = estadosIncidenciasService.crearEstadosIncidencias(estadoIncidencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(estadosIncidenciasCreada);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<E_EstadoIncidencia> actualizarEstadosIncidencias(@PathVariable Long id, @RequestBody E_EstadoIncidencia estadoIncidencia) {
        Optional<E_EstadoIncidencia>estadoIncidenciasActual = estadosIncidenciasService.obtenerEstadosIncidenciasPorId(id);
        if (estadoIncidenciasActual.isPresent()) {
            estadoIncidencia.setEstadoID(id);
            E_EstadoIncidencia actualizarEstadosIncidencias = estadosIncidenciasService.actualizarEstadosIncidencias(estadoIncidencia);
            return ResponseEntity.ok(actualizarEstadosIncidencias);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarEstadosIncidencias(@PathVariable Long id) {
        estadosIncidenciasService.eliminarEstadosIncidencias(id);
        return ResponseEntity.noContent().build();
    }
}



