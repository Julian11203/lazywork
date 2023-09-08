package com.lazywork.controlador;

import com.lazywork.entidad.Usuario;
import com.lazywork.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService servicioU;

    public UsuarioController(UsuarioService servicioU) {
        this.servicioU = servicioU;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Usuario>> findAll() {
        return new ResponseEntity<>(servicioU.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<Usuario>> findById(@PathVariable Long id) {
        if (servicioU.existsById(Long.valueOf(id))) {
            return new ResponseEntity<>(servicioU.findById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
        if(servicioU.existsById(usuario.getUsuarioID()) == false){
            servicioU.save(usuario);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


    @PutMapping("/re_save")
    public ResponseEntity<Usuario> re_save(@RequestBody Usuario usuario) {
        if(servicioU.existsById(usuario.getUsuarioID())){
            servicioU.save(usuario);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if(servicioU.existsById(Long.valueOf(id))){
            servicioU.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
