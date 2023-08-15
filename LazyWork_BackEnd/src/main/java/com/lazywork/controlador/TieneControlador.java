package com.lazywork.controlador;

import com.lazywork.entidad.Tiene;
import com.lazywork.servicios.TieneServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequestMapping("/api/tiene")
@RestController
public class TieneControlador {

    private final TieneServicio tieneServicio;
    @Autowired
    public TieneControlador(TieneServicio tieneServicio) {
        this.tieneServicio = tieneServicio;
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Tiene>> listarTiene(){
        return new ResponseEntity<>(tieneServicio.listarTiene(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Tiene> tienePorId(@PathVariable String id){
        if(tieneServicio.existeTiene(id)){
            return new ResponseEntity<>(tieneServicio.tienePorId(id), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
