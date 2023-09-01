package com.lazywork.controlador;

import com.lazywork.entidad.Incidencia;
import com.lazywork.entidad.Prioridad;
import com.lazywork.servicios.IncidenciaServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidencia")
public class IncidenciaControlador {

    IncidenciaServicio incidenciaServicio;

    public IncidenciaControlador(IncidenciaServicio incidenciaServicio) {
        this.incidenciaServicio = incidenciaServicio;
    }
    @GetMapping("/{id}")
    public  ResponseEntity<Incidencia> incidenciaPorId(@PathVariable String id){
        if(incidenciaServicio.existeIncidencia(id)){
            return new ResponseEntity<>(incidenciaServicio.incidenciaPorId(id), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Incidencia>> listaIncidencias(){
        return new ResponseEntity<>(incidenciaServicio.listaIncidencias(), HttpStatus.OK);
    }
    @PostMapping("/insertar")
    public ResponseEntity<Incidencia> insertarIncidencia(@RequestBody Incidencia incidencia){
        if(incidenciaServicio.existeIncidencia(incidencia.getNoIncidencia())){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }else{
            return new ResponseEntity<>(incidenciaServicio.insertarIncidencia(incidencia), HttpStatus.CREATED);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarIncidencia(@PathVariable String id){
        if(incidenciaServicio.existeIncidencia(id)){
            incidenciaServicio.eliminarIncidencia(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/actualizar")
    public ResponseEntity<Void> re_save(@RequestBody Incidencia incidencia){
        incidenciaServicio.insertarIncidencia(incidencia);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
