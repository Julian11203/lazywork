package com.lazywork.controlador;

import com.lazywork.entidad.Incidencia;
import com.lazywork.repositorio.UsuarioCrudRepository;
import com.lazywork.entidad.Prioridad;
import com.lazywork.servicios.IncidenciaServicio;
import com.lazywork.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/incidencia")
public class IncidenciaControlador {
    IncidenciaServicio incidenciaServicio;
    @Autowired
    public IncidenciaControlador(IncidenciaServicio incidenciaServicio) {
        this.incidenciaServicio = incidenciaServicio;
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Incidencia> finById(@PathVariable String id){
        if(incidenciaServicio.existsById(id)){
            return new ResponseEntity<>(incidenciaServicio.findById(id), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Incidencia>> findAll(){
        return new ResponseEntity<>(incidenciaServicio.findAll(), HttpStatus.OK);
    }
    @PostMapping("/insertar")
    public ResponseEntity<Void> save(@RequestBody Incidencia incidencia){
        if(incidenciaServicio.existsById(incidencia.getNoIncidencia())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }else{
            incidenciaServicio.save(incidencia);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        if(incidenciaServicio.existsById(id)){
            incidenciaServicio.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/actualizar")
    public ResponseEntity<Void> re_save(@RequestBody Incidencia incidencia){
        incidenciaServicio.save(incidencia);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}