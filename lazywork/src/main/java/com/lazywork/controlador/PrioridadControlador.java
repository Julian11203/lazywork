package com.lazywork.controlador;

import com.lazywork.entidad.Prioridad;
import com.lazywork.servicios.PrioridadServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prioridad")
public class PrioridadControlador {

    PrioridadServicio prioridadServicio;
    @Autowired
    public PrioridadControlador(PrioridadServicio prioridadServicio) {
        this.prioridadServicio = prioridadServicio;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Prioridad> findById(@PathVariable String id){
        if(prioridadServicio.findById(id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(prioridadServicio.findById(id).get() , HttpStatus.OK);
        }
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Prioridad>> findAll(){
        if(prioridadServicio.findAll().isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(prioridadServicio.findAll(), HttpStatus.OK);
        }
    }
    @PostMapping("/insertar")
    public ResponseEntity<Void> save(@RequestBody Prioridad prioridad){
        if(prioridadServicio.findById(prioridad.getIdPrioridad()).isEmpty()){
            prioridadServicio.save(prioridad);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        if(prioridadServicio.findById(id).isPresent()){
            prioridadServicio.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> re_save(@RequestBody Prioridad prioridad, @PathVariable String id){
        if(prioridadServicio.findById(prioridad.getIdPrioridad()).isPresent()){
            prioridadServicio.save(prioridad);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
