package com.example.demo.Controlador;


import com.example.demo.Entidad.E_CambioPrioridad;
import com.example.demo.Servicio.S_CambioPrioridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cambio-prioridad")
@CrossOrigin("*")
public class C_CambioPrioridad {

    @Autowired
    private S_CambioPrioridad cambioPrioridadService;

    @GetMapping
    public ResponseEntity<List<E_CambioPrioridad>> obtenerTodosLosCambioPrioridad() {
        List<E_CambioPrioridad> cambioPrioridad = cambioPrioridadService.obtenerTodosLosCambioPrioridad();
        return ResponseEntity.ok(cambioPrioridad);
    }

    @GetMapping("/{id}")
    public ResponseEntity<E_CambioPrioridad> obtenerCambioPrioridadPorId(@PathVariable String id) {
        Optional<E_CambioPrioridad> cambioPrioridad = cambioPrioridadService.obtenerCambioPrioridadPorId(id);
        if (cambioPrioridad.isPresent()) {
            return ResponseEntity.ok(cambioPrioridad.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<E_CambioPrioridad> crearCambioPrioridad(@RequestBody E_CambioPrioridad cambioPrioridad) {
        E_CambioPrioridad cambioPrioridadCreado = cambioPrioridadService.crearCambioPrioridad(cambioPrioridad);
        return ResponseEntity.status(HttpStatus.CREATED).body(cambioPrioridadCreado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCambioPrioridad(@PathVariable String id) {
        cambioPrioridadService.eliminarCambioPrioridad(id);
        return ResponseEntity.noContent().build();
    }
}
