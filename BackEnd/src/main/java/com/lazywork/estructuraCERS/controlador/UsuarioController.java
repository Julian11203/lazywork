package com.lazywork.estructuraCERS.controlador;

import com.lazywork.User.User;
import com.lazywork.estructuraCERS.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin("*")
// Cambia esto al origen de tu sitio web
public class UsuarioController {

    private final UsuarioServicio usuarioServicio;
    @Autowired
    public UsuarioController(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }


    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@Validated @RequestBody User nuevoUsuario, BindingResult result) {
        // Verifica si hay errores de validación
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        // Resto de la lógica para crear y guardar el usuario
        User usuarioGuardado = usuarioServicio.save(nuevoUsuario);
        return new ResponseEntity<>("User creado exitosamente", HttpStatus.CREATED);
    }
    @GetMapping("/buscarporid/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        Optional<User> usuario = usuarioServicio.findById(id);
        return usuario.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(usuarioServicio.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<User> usuario = usuarioServicio.findById(id);
        if (usuario.isPresent()) {
            usuarioServicio.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<User> actualizarUsuario(@RequestBody User usuarioActualizado, @PathVariable Long id) {
        try {
            User usuarioGuardado = usuarioServicio.actualizarUsuario(id, usuarioActualizado);
            return new ResponseEntity<>(usuarioGuardado, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}