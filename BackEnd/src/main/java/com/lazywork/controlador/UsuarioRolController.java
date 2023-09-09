package com.lazywork.controlador;

import com.lazywork.entidad.UsuarioRol;
import com.lazywork.servicios.UsuarioRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario-rol")
@CrossOrigin("*")
public class UsuarioRolController {

    @Autowired
    private UsuarioRolService servUsuarioRol;

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
    public ResponseEntity<UsuarioRol> save(@RequestBody UsuarioRol usuarioRol) {
        if(servUsuarioRol.existsById(String.valueOf(usuarioRol.getUsuarioRolID())) == false){
            servUsuarioRol.save(usuarioRol);
            return ResponseEntity.status(HttpStatus.CREATED).build();
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
