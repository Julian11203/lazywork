package com.example.demo.Controlador;


import com.example.demo.Entidad.E_PrioridadIncidencia;
import com.example.demo.Servicio.S_PrioridadesIncidencias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("prioridades")
@CrossOrigin("*")
public class C_PrioridadesIncidencias {

    @Autowired
    private S_PrioridadesIncidencias prioridadesIncidenciasService;

    @GetMapping("/listar")
    public ResponseEntity<List<E_PrioridadIncidencia>> obtenerTodasLasPrioridadesIncidencias() {
        List<E_PrioridadIncidencia> prioridadesIncidencias = prioridadesIncidenciasService.obtenerTodasLasPrioridadesIncidencias();
        return ResponseEntity.ok(prioridadesIncidencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<E_PrioridadIncidencia> obtenerPrioridadesIncidenciasPorId(@PathVariable Long id) {
        Optional<E_PrioridadIncidencia> prioridadesIncidencias = prioridadesIncidenciasService.obtenerPrioridadesIncidenciasPorId(id);
        if (prioridadesIncidencias.isPresent()) {
            return ResponseEntity.ok(prioridadesIncidencias.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<E_PrioridadIncidencia> crearPrioridadesIncidencias(@RequestBody E_PrioridadIncidencia prioridadesIncidencias) {
        E_PrioridadIncidencia prioridadesIncidenciasCreada = prioridadesIncidenciasService.crearPrioridadesIncidencias(prioridadesIncidencias);
        return ResponseEntity.status(HttpStatus.CREATED).body(prioridadesIncidenciasCreada);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<E_PrioridadIncidencia> actualizarPrioridadesIncidencias(@PathVariable Long id, @RequestBody E_PrioridadIncidencia prioridadesIncidencias) {
        Optional<E_PrioridadIncidencia> prioridadesIncidenciasActual = prioridadesIncidenciasService.obtenerPrioridadesIncidenciasPorId(id);
        if (prioridadesIncidenciasActual.isPresent()) {
            prioridadesIncidencias.setId(id);
            E_PrioridadIncidencia prioridadesIncidenciasActualizada = prioridadesIncidenciasService.actualizarPrioridadesIncidencias(prioridadesIncidencias);
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
