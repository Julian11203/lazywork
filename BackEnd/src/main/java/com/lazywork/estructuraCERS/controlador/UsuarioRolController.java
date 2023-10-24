package com.lazywork.estructuraCERS.controlador;

import com.lazywork.estructuraCERS.entidad.UsuarioRol;
import com.lazywork.estructuraCERS.servicios.RolService;
import com.lazywork.estructuraCERS.servicios.UsuarioRolService;
import com.lazywork.estructuraCERS.servicios.UsuarioServicio;
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

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody UsuarioRol usuarioRol) {
        if(servUsuarioRol.existsById(String.valueOf(usuarioRol.getUsuarioRolID())) == false){
            if(servUsuario.findById(Long.valueOf(String.valueOf(usuarioRol.getUsuario().getId()))).isPresent() && servRol.existsById(String.valueOf(usuarioRol.getRol().getRolID())) && servUsuarioRol.existsUsuario(String.valueOf(usuarioRol.getUsuario().getId())).isEmpty()){
                servUsuarioRol.save(usuarioRol);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }else{
                return ResponseEntity.badRequest().build();
            }
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
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
