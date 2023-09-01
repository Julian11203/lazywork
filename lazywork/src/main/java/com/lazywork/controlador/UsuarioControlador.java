package com.lazywork.controlador;

import com.lazywork.entidad.Usuario;
import com.lazywork.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin("*")
public class UsuarioControlador {
    UsuarioServicio usuarioServicio;
    @Autowired
    public UsuarioControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable String id){
        if(usuarioServicio.findById(id).isEmpty()){
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usuarioServicio.findById(id).get(),HttpStatus.OK);

        }
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> findAll(){
        if (usuarioServicio.findall().isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(usuarioServicio.findall(),HttpStatus.OK);
        }

    }
    @PostMapping("/insertar")
    public ResponseEntity<Void> save(@RequestBody Usuario usuario){
        if (usuarioServicio.findById(usuario.getIdUser()).isEmpty()){
            usuarioServicio.save(usuario);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        if (usuarioServicio.findById(id).isPresent()){
            usuarioServicio.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/actualizar")
    public ResponseEntity<Void> re_save(@RequestBody Usuario usuario){
        if (usuarioServicio.existeUsuario(usuario.getIdUser())){
            usuarioServicio.save(usuario);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
