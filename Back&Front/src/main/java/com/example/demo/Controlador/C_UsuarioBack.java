package com.example.demo.Controlador;

import com.example.demo.Entidad.E_Usuarioback;
import com.example.demo.Servicio.S_Usuarioback;
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
public class C_UsuarioBack {



    private final S_Usuarioback servicioUsuarioback;

    @Autowired
    public C_UsuarioBack(S_Usuarioback servicioUsuarioback) {
        this.servicioUsuarioback = servicioUsuarioback;
    }


    @PostMapping("/crear")
    public ResponseEntity<?> save(@Validated @RequestBody E_Usuarioback nuevoUsuarioback, BindingResult result) {
        // Verifica si hay errores de validación
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        // Resto de la lógica para crear y guardar el usuario
        E_Usuarioback usuariobackGuardado = servicioUsuarioback.save(nuevoUsuarioback);
        return new ResponseEntity<>("E_Usuario creado exitosamente", HttpStatus.CREATED);
    }

    @GetMapping("/buscarporid/{id}")
    public ResponseEntity<E_Usuarioback> findById(@PathVariable Long usuarioid) {
        Optional<E_Usuarioback> usuario = servicioUsuarioback.findById(usuarioid);
        return usuario.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<E_Usuarioback>> findAll() {
        return new ResponseEntity<>(servicioUsuarioback.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long usuarioid) {
        Optional<E_Usuarioback> usuario = servicioUsuarioback.findById(usuarioid);
        if (usuario.isPresent()) {
            servicioUsuarioback.delete(Math.toIntExact(usuarioid));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<E_Usuarioback> actualizarUsuario(@RequestBody E_Usuarioback usuariobackActualizado, @PathVariable int id) {
        try {
            E_Usuarioback usuariobackGuardado = servicioUsuarioback.actualizarUsuario(id, usuariobackActualizado);
            return new ResponseEntity<>(usuariobackGuardado, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


