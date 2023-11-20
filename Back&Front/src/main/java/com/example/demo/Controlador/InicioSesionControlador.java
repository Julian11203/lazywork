package com.example.demo.Controlador;

import com.example.demo.Entidad.InicioSesion;
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
    public ResponseEntity<?> insertarInicioSesion(@RequestBody InicioSesion inicioSesion) {
        // Verificar si se proporciona un ID de usuario válido
        Long usuarioId = inicioSesion.getId();
        if (usuarioId == null || usuarioId <= 0) {
            return new ResponseEntity<>("El ID del usuario no es válido", HttpStatus.BAD_REQUEST);
        }

        try {
            // Convertir usuarioId a int (asumiendo que tu método existeUsuario toma un parámetro int)
            int usuarioIdInt = usuarioId.intValue();

            // Verificar si el usuario existe
            if (usuarioServicio.existeUsuario(usuarioIdInt)) {
                // Establecer fecha_hora_inicio en la marca de tiempo actual
                inicioSesion.setFechaHoraInicio(LocalDateTime.now());

                // Establecer el tiempo de sesión (asumiendo que deseas establecerlo en la marca de tiempo actual)
                inicioSesion.setTiempodesesion(String.valueOf(LocalDateTime.now()));

                // Insertar el nuevo inicioSesion
                InicioSesion nuevoInicioSesion = inicioSesionServicios.insertarInicioSesion(inicioSesion);

                // Verificar si la inserción fue exitosa
                if (nuevoInicioSesion != null) {
                    return new ResponseEntity<>(nuevoInicioSesion, HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>("No se pudo crear el inicio de sesión", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity<>("El usuario no existe", HttpStatus.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("El ID del usuario no es un número válido", HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarInicioSesion(
            @PathVariable Long id,
            @RequestBody InicioSesion inicioSesionActualizado
    ) {
        if (inicioSesionServicios.existeInicioSesion(id)) {
            InicioSesion inicioSesion = inicioSesionServicios.actualizarInicioSesion(id, inicioSesionActualizado);
            if (inicioSesion != null) {
                return ResponseEntity.ok(inicioSesion);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el inicio de sesión");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inicio de sesión no encontrado");
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
