package com.lazywork.controlador;

import com.lazywork.entidad.Estado;
import com.lazywork.servicios.EstadoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api/estado")
@RestController
public class EstadoControlador {
    private final EstadoServicio estadoServicio;

    public EstadoControlador(EstadoServicio estadoServicio) {
        this.estadoServicio = estadoServicio;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Estado>> listarEstados() {
        List<Estado> estados = estadoServicio.listarEstados();
        return new ResponseEntity<>(estados, HttpStatus.OK);
    }

    @GetMapping("/{idEstado}")
    public ResponseEntity<Estado> obtenerEstadoPorIdEstado(@PathVariable String idEstado) {
        Estado estado = estadoServicio.obtenerEstadoPorIdEstado(idEstado);
        if (estado != null) {
            return new ResponseEntity<>(estado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/insertar")
    public ResponseEntity<Estado> crearEstado(@RequestBody Estado nuevoEstado) {
        Estado estadoExistente = estadoServicio.obtenerEstadoPorIdEstado(nuevoEstado.getIdEstado());
        if (estadoExistente != null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            Estado estadoCreado = estadoServicio.crearEstado(nuevoEstado);
            return new ResponseEntity<>(estadoCreado, HttpStatus.CREATED);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Estado> actualizarEstado(@RequestBody Estado estado) {
        Estado estadoActualizado = estadoServicio.actualizarEstado(estado);
        if (estadoActualizado != null) {
            return new ResponseEntity<>(estadoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarEstado(@PathVariable String id){
        Estado estado = estadoServicio.obtenerEstadoPorIdEstado(id);
        if (estado != null) {
            estadoServicio.eliminarEstado(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
