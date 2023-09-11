package com.lazywork.controlador;

import com.lazywork.entidad.UsuarioRol;
import com.lazywork.servicios.RolService;
import com.lazywork.servicios.UsuarioRolService;
import com.lazywork.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario-rol")
@CrossOrigin("*")
public class UsuarioRolController {

    private UsuarioRolService servUsuarioRol;
    private UsuarioServicio servUsuario;
    private RolService servRol;

    @Autowired
    public UsuarioRolController(UsuarioRolService servUsuarioRol, UsuarioServicio servUsuario, RolService servRol) {
        this.servUsuarioRol = servUsuarioRol;
        this.servUsuario = servUsuario;
        this.servRol = servRol;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UsuarioRol>> findAlll() {
        if(servUsuarioRol.findAll().isEmpty()){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.ok(servUsuarioRol.findAll());
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<UsuarioRol> findById(@PathVariable String id) {
        if (servUsuarioRol.existsById(id)) {
            return ResponseEntity.ok(servUsuarioRol.findById(id).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // Corregir
    @PostMapping("/save/{usuarioID}/{rolID}")
    public ResponseEntity<Void> save(@PathVariable String usuarioID, @PathVariable String rolID) {
        if((servUsuario.findById(usuarioID).isPresent() && servRol.existsById(rolID)) && (servUsuarioRol.existsUsuario(usuarioID).isEmpty())){
            UsuarioRol usuarioRol = new UsuarioRol();
            usuarioRol.setUsuario(servUsuario.findById(usuarioID).get());
            usuarioRol.setRol(servRol.findById(rolID).get());
            servUsuarioRol.save(usuarioRol);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        if(servUsuarioRol.existsById(id)){
            servUsuarioRol.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return  ResponseEntity.notFound().build();
        }
    }
}
