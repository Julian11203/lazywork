package com.lazywork.controlador;

import com.lazywork.entidad.UsuarioRol;
import com.lazywork.servicios.UsuarioRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario-roles")
public class UsuarioRolesController {

    @Autowired
    private UsuarioRolesService usuarioRolesService;

    @GetMapping
    public ResponseEntity<List<UsuarioRol>> obtenerTodosLosUsuarioRoles() {
        List<UsuarioRol> usuarioRoles = usuarioRolesService.obtenerTodosLosUsuarioRoles();
        return ResponseEntity.ok(usuarioRoles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioRol> obtenerUsuarioRolesPorId(@PathVariable String id) {
        Optional<UsuarioRol> usuarioRoles = usuarioRolesService.obtenerUsuarioRolesPorId(id);
        if (usuarioRoles.isPresent()) {
            return ResponseEntity.ok(usuarioRoles.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioRol> crearUsuarioRoles(@RequestBody UsuarioRol usuarioRoles) {
        UsuarioRol usuarioRolesCreado = usuarioRolesService.crearUsuarioRoles(usuarioRoles);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRolesCreado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuarioRoles(@PathVariable String id) {
        usuarioRolesService.eliminarUsuarioRoles(id);
        return ResponseEntity.noContent().build();
    }
}
