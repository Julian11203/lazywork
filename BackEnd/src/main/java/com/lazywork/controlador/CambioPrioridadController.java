package com.lazywork.controlador;


import com.lazywork.entidad.CambioPrioridad;
import com.lazywork.servicios.CambioPrioridadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cambio-prioridad")
public class CambioPrioridadController {

    @Autowired
    private CambioPrioridadService cambioPrioridadService;

    @GetMapping
    public ResponseEntity<List<CambioPrioridad>> obtenerTodosLosCambioPrioridad() {
        List<CambioPrioridad> cambioPrioridad = cambioPrioridadService.obtenerTodosLosCambioPrioridad();
        return ResponseEntity.ok(cambioPrioridad);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CambioPrioridad> obtenerCambioPrioridadPorId(@PathVariable String id) {
        Optional<CambioPrioridad> cambioPrioridad = cambioPrioridadService.obtenerCambioPrioridadPorId(id);
        if (cambioPrioridad.isPresent()) {
            return ResponseEntity.ok(cambioPrioridad.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CambioPrioridad> crearCambioPrioridad(@RequestBody CambioPrioridad cambioPrioridad) {
        CambioPrioridad cambioPrioridadCreado = cambioPrioridadService.crearCambioPrioridad(cambioPrioridad);
        return ResponseEntity.status(HttpStatus.CREATED).body(cambioPrioridadCreado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCambioPrioridad(@PathVariable String id) {
        cambioPrioridadService.eliminarCambioPrioridad(id);
        return ResponseEntity.noContent().build();
    }
}
