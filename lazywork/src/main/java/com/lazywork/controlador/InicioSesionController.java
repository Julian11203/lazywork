package com.lazywork.controlador;


import com.lazywork.entidad.InicioSesion;
import com.lazywork.servicios.InicioSesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inicios-sesion")
public class InicioSesionController {

    @Autowired
    private InicioSesionService inicioSesionService;

    @GetMapping("/listar")
    public ResponseEntity<List<InicioSesion>> obtenerTodosLosIniciosSesion() {
        List<InicioSesion> iniciosSesion = inicioSesionService.obtenerTodosLosIniciosSesion();
        return ResponseEntity.ok(iniciosSesion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InicioSesion> obtenerInicioSesionPorId(@PathVariable String id) {
        Optional<InicioSesion> inicioSesion = inicioSesionService.obtenerInicioSesionPorId(id);
        if (inicioSesion.isPresent()) {
            return ResponseEntity.ok(inicioSesion.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<InicioSesion> crearInicioSesion(@RequestBody InicioSesion inicioSesion) {
        InicioSesion inicioSesionCreado = inicioSesionService.crearInicioSesion(inicioSesion);
        return ResponseEntity.status(HttpStatus.CREATED).body(inicioSesionCreado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInicioSesion(@PathVariable String id) {
        inicioSesionService.eliminarInicioSesion(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<InicioSesion> actualizarInicioSesion(@PathVariable String id, @RequestBody InicioSesion inicioSesion) {
        Optional<InicioSesion> inicioSesionExistente = inicioSesionService.obtenerInicioSesionPorId(id);
        if (inicioSesionExistente.isPresent()) {
            InicioSesion inicioSesionActualizado = inicioSesionService.actualizarInicioSesion(id, inicioSesion);
            return ResponseEntity.ok(inicioSesionActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
