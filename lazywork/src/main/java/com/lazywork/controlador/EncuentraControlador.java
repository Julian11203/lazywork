package com.lazywork.controlador;

import com.lazywork.entidad.Encuentra;
import com.lazywork.servicios.EncuentraServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/encuentra")
public class EncuentraControlador {

    private final EncuentraServicio encuentraServicio;
    @Autowired
    public EncuentraControlador(EncuentraServicio encuentraServicio) {
        this.encuentraServicio = encuentraServicio;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Encuentra> encuentraPorId(@PathVariable String id){
        if(encuentraServicio.existeEncuentra(id)){
           return new ResponseEntity<>(encuentraServicio.encuentraPorId(id), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Encuentra>> listaEncuentra(){
        return new ResponseEntity<>(encuentraServicio.listarEncuentra(), HttpStatus.OK);
    }
    @PostMapping("/insertar")
    public ResponseEntity<Encuentra> insertarEncuentra(@RequestBody Encuentra encuentra){
        if(encuentraServicio.existeEncuentra(encuentra.getnRegistro())){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }else{
            return new ResponseEntity<>(encuentraServicio.insertarEncuentra(encuentra), HttpStatus.CREATED);
        }
    }
    @PutMapping("/actualizar")
    public ResponseEntity<Encuentra> actualizarEncuentra(@RequestBody Encuentra encuentra){
        if(encuentraServicio.existeEncuentra(encuentra.getnRegistro())){
            return new ResponseEntity<>(encuentraServicio.insertarEncuentra(encuentra), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarEncuentra(@PathVariable String id){
        if(encuentraServicio.existeEncuentra(id)){
            encuentraServicio.eliminarEncuentra(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
