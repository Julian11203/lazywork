package com.lazywork.controlador;

import com.lazywork.entidad.Incidencia;
import com.lazywork.servicios.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/incidencia")
public class IncidenciaController {

    @Autowired
    private IncidenciaService incidenciaService;

    @GetMapping("/lisar")
    public ResponseEntity<List<Incidencia>> obtenerTodasLasIncidencias() {
        List<Incidencia> incidencias = incidenciaService.obtenerTodasLasIncidencias();
        return ResponseEntity.ok(incidencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incidencia> obtenerIncidenciaPorId(@PathVariable Long id) {
        Optional<Incidencia> incidencia = incidenciaService.obtenerIncidenciaPorId(id);
        if (incidencia.isPresent()) {
            return ResponseEntity.ok(incidencia.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<Incidencia> crearIncidencia(@RequestBody Incidencia incidenciaG) {
        Optional<Incidencia> incidencia = incidenciaService.obtenerIncidenciaPorId(incidenciaG.getIncidenciaID());
        if (incidencia.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            incidenciaService.crearIncidencia(incidenciaG);
            return ResponseEntity.ok(incidencia.get());
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Incidencia> actualizarIncidencia(@RequestBody Incidencia incidenciaG) {
        Optional<Incidencia> incidencia = incidenciaService.obtenerIncidenciaPorId(incidenciaG.getIncidenciaID());
        if (incidencia.isPresent()) {
            incidenciaService.crearIncidencia(incidenciaG);
            return ResponseEntity.ok(incidencia.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarIncidencia(@PathVariable Long id) {
        incidenciaService.eliminarIncidencia(id);
        return ResponseEntity.noContent().build();
    }
}
