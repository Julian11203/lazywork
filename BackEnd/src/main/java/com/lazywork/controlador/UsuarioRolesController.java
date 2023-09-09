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
@RequestMapping("/usuario-rol")
@CrossOrigin("*")
public class UsuarioRolesController {

    @Autowired
    private UsuarioRolesService servUsuarioRol;

    @GetMapping
    public ResponseEntity<List<UsuarioRol>> findAlll() {
        if(servUsuarioRol.findAll().isEmpty()){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(servUsuarioRol.findAll(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioRol> findById(@PathVariable Long id) {
        Optional<UsuarioRol> usuarioRoles = servUsuarioRol.findById(id);
        if (usuarioRoles.isPresent()) {
            return ResponseEntity.ok(usuarioRoles.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioRol> save(@RequestBody UsuarioRol usuarioRoles) {
        UsuarioRol usuarioRolesCreado = servUsuarioRol.save(usuarioRoles);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRolesCreado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        servUsuarioRol.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
