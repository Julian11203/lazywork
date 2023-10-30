package com.lazywork.estructuraCERS.controlador;


import com.lazywork.estructuraCERS.entidad.PrioridadIncidencia;
import com.lazywork.estructuraCERS.servicios.PrioridadesIncidenciasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prioridades")
@CrossOrigin("*")
public class PrioridadesIncidenciasController {

    @Autowired
    private PrioridadesIncidenciasService prioridadesIncidenciasService;

    @GetMapping("/listar")
    public ResponseEntity<List<PrioridadIncidencia>> obtenerTodasLasPrioridadesIncidencias() {
        List<PrioridadIncidencia> prioridadesIncidencias = prioridadesIncidenciasService.obtenerTodasLasPrioridadesIncidencias();
        return ResponseEntity.ok(prioridadesIncidencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrioridadIncidencia> obtenerPrioridadesIncidenciasPorId(@PathVariable Long id) {
        Optional<PrioridadIncidencia> prioridadesIncidencias = prioridadesIncidenciasService.obtenerPrioridadesIncidenciasPorId(id);
        if (prioridadesIncidencias.isPresent()) {
            return ResponseEntity.ok(prioridadesIncidencias.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<PrioridadIncidencia> crearPrioridadesIncidencias(@RequestBody PrioridadIncidencia prioridadesIncidencias) {
        PrioridadIncidencia prioridadesIncidenciasCreada = prioridadesIncidenciasService.crearPrioridadesIncidencias(prioridadesIncidencias);
        return ResponseEntity.status(HttpStatus.CREATED).body(prioridadesIncidenciasCreada);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PrioridadIncidencia> actualizarPrioridadesIncidencias(@PathVariable Long id, @RequestBody PrioridadIncidencia prioridadesIncidencias) {
        Optional<PrioridadIncidencia> prioridadesIncidenciasActual = prioridadesIncidenciasService.obtenerPrioridadesIncidenciasPorId(id);
        if (prioridadesIncidenciasActual.isPresent()) {
            prioridadesIncidencias.setId(id);
            PrioridadIncidencia prioridadesIncidenciasActualizada = prioridadesIncidenciasService.actualizarPrioridadesIncidencias(prioridadesIncidencias);
            return ResponseEntity.ok(prioridadesIncidenciasActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarPrioridadesIncidencias(@PathVariable Long id) {
        prioridadesIncidenciasService.eliminarPrioridadesIncidencias(id);
        return ResponseEntity.noContent().build();
    }
}
