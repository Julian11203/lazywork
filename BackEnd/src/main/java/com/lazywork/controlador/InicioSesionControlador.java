package com.lazywork.controlador;

import com.lazywork.entidad.InicioSesion;
import com.lazywork.servicios.InicioSesionServicios;
import com.lazywork.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/iniciosesion")
public class InicioSesionControlador {
    private final InicioSesionServicios inicioSesionServicios;
    @Autowired
    private UsuarioServicio usuarioServicio;
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

    public ResponseEntity<?> insertarInicioSesion(@RequestBody InicioSesion inicioSesion) {
        if (inicioSesion.getUsuario() == null) {
            return new ResponseEntity<>("El usuario no puede ser nulo", HttpStatus.BAD_REQUEST);
        }

        Long usuarioId = inicioSesion.getUsuario().getId();
        System.out.println(usuarioId);
        if (usuarioId != null && usuarioServicio.existeUsuario(usuarioId)) {
            inicioSesion.setTiempodesesion(LocalDateTime.now()); // Establece el valor del tiempo de sesión
            InicioSesion nuevoInicioSesion = inicioSesionServicios.insertarInicioSesion(inicioSesion);
            if (nuevoInicioSesion != null) {
                return new ResponseEntity<>(nuevoInicioSesion, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("No se pudo crear el inicio de sesión", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("El usuario no existe", HttpStatus.BAD_REQUEST);
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