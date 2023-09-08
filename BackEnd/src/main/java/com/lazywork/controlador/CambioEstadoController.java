package com.lazywork.controlador;


import com.lazywork.entidad.CambioEstado;
import com.lazywork.servicios.CambioEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cambio-estado")
public class CambioEstadoController {

    @Autowired
    private CambioEstadoService cambioEstadoService;

    @GetMapping("/listar")
    public ResponseEntity<List<CambioEstado>> obtenerTodasLosCambioEstado() {
        List<CambioEstado> cambioEstado = cambioEstadoService.obtenerTodosLosCambioEstado();
        return ResponseEntity.ok(cambioEstado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CambioEstado> obtenerCambioestadoPorId(@PathVariable Long id) {
        Optional<CambioEstado> cambioestado = cambioEstadoService.obtenerCambioEstadoPorId(id);
        if (cambioestado.isPresent()) {
            return ResponseEntity.ok(cambioestado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<CambioEstado> crearCambioestado(@RequestBody CambioEstado cambioEstado) {
        CambioEstado cambioestadoCreado = cambioEstadoService.crearCambioEstado(cambioEstado);
        return ResponseEntity.status(HttpStatus.CREATED).body(cambioestadoCreado);
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<CambioEstado> actualizarCambioestado(@PathVariable Long id, @RequestBody CambioEstado cambioEstado){
        Optional<CambioEstado> cambioestadoActual = cambioEstadoService.obtenerCambioEstadoPorId((id));
        if (cambioestadoActual.isPresent()) {
            cambioEstado.setId(id);
            CambioEstado cambioestadoActualizado = cambioEstadoService.actualizarCambioEstado(cambioEstado);
            return ResponseEntity.ok(cambioestadoActualizado);
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity<Void> eliminarcambioEstado(@PathVariable Long id) {
        cambioEstadoService.eliminarCambioEstado(id);
        return ResponseEntity.noContent().build();
    }
}
