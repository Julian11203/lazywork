package com.lazywork.estructuraCERS.controlador;

import com.lazywork.estructuraCERS.entidad.EstadoIncidencia;
import com.lazywork.estructuraCERS.servicios.EstadosIncidenciasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/estados")

public class EstadoIncidenciaController {

    @Autowired
    EstadosIncidenciasService estadosIncidenciasService;

    @GetMapping("/listar")
    public ResponseEntity<List<EstadoIncidencia>> obtenerTodasLosEstadosIncidencia() {
        List<EstadoIncidencia> estadoIncidencias = estadosIncidenciasService.obtenerTodosLosEstadosIncidencias();
        return ResponseEntity.ok(estadoIncidencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoIncidencia> obtenerEstadosIncidenciasPorId(@PathVariable Long id) {
        Optional<EstadoIncidencia> estadoIncidencia =estadosIncidenciasService.obtenerEstadosIncidenciasPorId(id);
        if (estadoIncidencia.isPresent()) {
            return ResponseEntity.ok(estadoIncidencia.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping ("/crear")
    public ResponseEntity<EstadoIncidencia> crearEstadosIncidencias(@RequestBody EstadoIncidencia estadoIncidencia) {
        EstadoIncidencia estadosIncidenciasCreada = estadosIncidenciasService.crearEstadosIncidencias(estadoIncidencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(estadosIncidenciasCreada);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<EstadoIncidencia> actualizarEstadosIncidencias(@PathVariable Long id, @RequestBody EstadoIncidencia estadoIncidencia) {
        Optional<EstadoIncidencia>estadoIncidenciasActual = estadosIncidenciasService.obtenerEstadosIncidenciasPorId(id);
        if (estadoIncidenciasActual.isPresent()) {
            estadoIncidencia.setEstadoID(id);
            EstadoIncidencia actualizarEstadosIncidencias = estadosIncidenciasService.actualizarEstadosIncidencias(estadoIncidencia);
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



