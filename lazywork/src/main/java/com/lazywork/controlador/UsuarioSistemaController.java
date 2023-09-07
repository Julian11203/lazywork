package com.lazywork.controlador;

import com.lazywork.entidad.UsuarioSistema;
import com.lazywork.servicios.UsuarioSistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioSistemaController {

    @Autowired
    private UsuarioSistemaService usuarioSistemaService;

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioSistema>> obtenerTodosLosUsuarios() {
        List<UsuarioSistema> usuarios = usuarioSistemaService.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioSistema> obtenerUsuarioPorId(@PathVariable String id) {
        Optional<UsuarioSistema> usuario = usuarioSistemaService.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<UsuarioSistema> crearUsuario(@RequestBody UsuarioSistema usuario) {
        // Asegúrate de que el ID del usuario esté vacío o sea nulo antes de guardar
        if (usuario.getUsuarioID() != null) {
            return ResponseEntity.badRequest().body(null); // ID ya existe, devuelve un error 400 Bad Request
        }

        UsuarioSistema nuevoUsuario = usuarioSistemaService.save(usuario);

        if (nuevoUsuario != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Error interno del servidor
        }
    }


    @PutMapping("/actualizar/{id}")
    public ResponseEntity<UsuarioSistema> actualizarUsuario(@PathVariable String id, @RequestBody UsuarioSistema usuario) {
        if (usuarioSistemaService.existeUsuario(id)) {
            usuarioSistemaService.save(usuario);
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable String id) {
        if (usuarioSistemaService.existeUsuario(id)) {
            usuarioSistemaService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
