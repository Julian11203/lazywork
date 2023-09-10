package com.lazywork.controlador;

import com.lazywork.entidad.InicioSesion;
import com.lazywork.servicios.InicioSesionServicios;
import com.lazywork.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/iniciosesion")
@CrossOrigin("*")
public class InicioSesionControlador {
    private final InicioSesionServicios inicioSesionServicios;

    @Autowired
    public InicioSesionControlador(InicioSesionServicios inicioSesionServicios) {
        this.inicioSesionServicios = inicioSesionServicios;
    }

    @GetMapping("/buscarporid/{id}")
    public ResponseEntity<InicioSesion> findInicioSesionById(@PathVariable Long id) {
        Optional<InicioSesion> inicioSesion = inicioSesionServicios.findInicioSesionById(id);
        return inicioSesion.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<InicioSesion>> listarIniciosSesion() {
        List<InicioSesion> iniciosSesion = inicioSesionServicios.listaIniciosSesion();
        return new ResponseEntity<>(iniciosSesion, HttpStatus.OK);
    }

    @PostMapping("/insertar")
    public ResponseEntity<InicioSesion> insertarInicioSesion(@RequestBody InicioSesion inicioSesion) {
        // Asegúrate de que el usuario asociado a inicioSesion exista en la base de datos antes de insertar.
        // Puedes hacer la verificación en el servicio antes de realizar la inserción.
        String usuarioId = String.valueOf(inicioSesion.getUsuario().getId()); // Suponiendo que tienes un método getId() en Usuario
        if (usuarioId != null && UsuarioServicio.existeUsuario(usuarioId)) {
            InicioSesion nuevoInicioSesion = inicioSesionServicios.insertarInicioSesion(inicioSesion);
            if (nuevoInicioSesion != null) {
                return new ResponseEntity<>(nuevoInicioSesion, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // El usuario no existe en la base de datos.
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<InicioSesion> actualizarInicioSesion(
            @PathVariable Long id,
            @RequestBody InicioSesion inicioSesionActualizado
    ) {
        // Asegúrate de que el usuario asociado a inicioSesionActualizado exista en la base de datos antes de actualizar.
        // Puedes hacer la verificación en el servicio antes de realizar la actualización.
        InicioSesion inicioSesion = inicioSesionServicios.actualizarInicioSesion(id, inicioSesionActualizado);
        if (inicioSesion != null) {
            return new ResponseEntity<>(inicioSesion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarInicioSesion(@PathVariable Long id) {
        if (inicioSesionServicios.existeInicioSesion(id)) {
            inicioSesionServicios.eliminarInicioSesion(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
