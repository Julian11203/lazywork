package com.lazywork.controlador;

import com.lazywork.entidad.Inicio;
import com.lazywork.servicios.InicioServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inicio")
@CrossOrigin("*")
public class InicioControlador {

    private final InicioServicio inicioServicio;

    public InicioControlador(InicioServicio inicioServicio) {
        this.inicioServicio = inicioServicio;
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Inicio> actualizarInicio(@RequestBody Inicio nuevoInicio, @PathVariable String id) {
        if (inicioServicio.existeInicio(id)) {
            nuevoInicio.setIdInicio(id); // Mantener el mismo ID al actualizar
            Inicio inicioActualizado = inicioServicio.actualizarInicio(nuevoInicio);
            return new ResponseEntity<>(inicioActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inicio> obtenerInicioPorId(@PathVariable String id) {
        if (inicioServicio.existeInicio(id)) {
            return new ResponseEntity<>(inicioServicio.obtenerInicioPorId(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Inicio>> listarInicios() {
        return new ResponseEntity<>(inicioServicio.listarInicios(), HttpStatus.OK);
    }

    @PostMapping("/insertar")
    public ResponseEntity<Inicio> insertarInicio(@RequestBody Inicio inicio) {
        return new ResponseEntity<>(inicioServicio.insertarInicio(inicio), HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarInicio(@PathVariable String id) {
        if (inicioServicio.existeInicio(id)) {
            inicioServicio.eliminarInicio(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
