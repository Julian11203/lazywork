package com.lazywork.controlador;

import com.lazywork.entidad.Incidencia;
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

}
