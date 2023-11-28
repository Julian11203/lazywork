package com.example.demo.Controlador;

import com.example.demo.Entidad.InicioSesion;
import com.example.demo.Entidad.Usuarioback;
import com.example.demo.Servicio.InicioSesionServicios;
import com.example.demo.Servicio.ServicioUsuarioback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/iniciosesion")
@CrossOrigin("*")
public class InicioSesionControlador {
    private final InicioSesionServicios inicioSesionServicios;

    @Autowired
    private ServicioUsuarioback usuarioServicio;

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
    public ResponseEntity<InicioSesion> insertarInicioSesion(@RequestBody InicioSesion nuevoInicioSesion) {
        InicioSesion inicioSesionInsertado = inicioSesionServicios.insertarInicioSesion(nuevoInicioSesion);

        if (inicioSesionInsertado != null) {
            return new ResponseEntity<>(inicioSesionInsertado, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarInicioSesion(
            @PathVariable Long id,
            @RequestBody InicioSesion inicioSesionActualizado
    ) {
        InicioSesion inicioSesion = inicioSesionServicios.actualizarInicioSesion(id, inicioSesionActualizado);
        if (inicioSesion != null) {
            return ResponseEntity.ok(inicioSesion);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el inicio de sesión");
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