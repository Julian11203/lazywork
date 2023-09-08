package com.lazywork.controlador;

import com.lazywork.entidad.Usuario;
import com.lazywork.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    private UsuarioService servicioU;
    private HttpStatus status;
    private ArrayList respuesta = new ArrayList<>();
    @Autowired
    public UsuarioController(UsuarioService servicioU) {
        this.servicioU = servicioU;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Usuario>> findAll() {
        respuesta.clear();
        status = HttpStatus.OK;
        if(!servicioU.findAll().isEmpty()){
            respuesta.add(servicioU.findAll());
            return new ResponseEntity<>(respuesta, status);
        }else{
            return new ResponseEntity<>(status);
        }

    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ArrayList> findById(@PathVariable String id) {
        respuesta.clear();
        if (servicioU.existsById(Long.valueOf(id))) {
            respuesta.add(servicioU.findById(id).get());
            status = HttpStatus.OK;
        } else {
            respuesta.add("No se encontro el usuario");
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(respuesta, status);
    }

    @PostMapping("/save")
    public ResponseEntity<ArrayList> save(@RequestBody Usuario usuario) {
        respuesta.clear();
        if(servicioU.existsById(usuario.getUsuarioID()) == false){
            servicioU.save(usuario);
            respuesta.add("Se ha creado el usuario exitosamente");
            status = HttpStatus.CREATED;

        }
        else{
            respuesta.add("El usuario ya existe");
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(respuesta, status);
    }


    @PutMapping("/re_save")
    public ResponseEntity<ArrayList> re_save(@RequestBody Usuario usuario) {
        respuesta.clear();
        if(servicioU.existsById(usuario.getUsuarioID())){
            servicioU.save(usuario);
            respuesta.add("Se ha actualizado correctamente");
            status = HttpStatus.OK;
        }
        else{
            respuesta.add("El usuario a actualizar no existe");
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(respuesta, status);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<ArrayList> deleteById(@PathVariable Long id) {
        respuesta.clear();
        if(servicioU.existsById(Long.valueOf(id))){
            servicioU.deleteById(id);
            respuesta.add("Se elimino correctamente");
            status = HttpStatus.OK;
        }
        else{
            respuesta.add("Imposible eliminar, el usuario no existe");
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(respuesta, status);
    }
}
