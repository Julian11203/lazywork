package com.lazywork.controlador;

import com.lazywork.entidad.Tiene;
import com.lazywork.entidad.Usuario;
import com.lazywork.servicios.TieneServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api/tiene")
@RestController
@CrossOrigin("*")
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
    @PostMapping("/insertar")
    public ResponseEntity<Tiene> insertarTiene(@RequestBody Tiene tiene){
        System.out.println(tiene.getIncidencia().getNoIncidencia());
        System.out.println(tiene.getPrioridad().getIdPrioridad());
        if(tieneServicio.existeTiene(tiene.getnRegistro())){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }else{
            return new ResponseEntity<>(tieneServicio.insertarTiene(tiene), HttpStatus.CREATED);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarTiene(@PathVariable String id){
        if(tieneServicio.existeTiene(id)){
            tieneServicio.eliminarTiene(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/actualizar")
    public ResponseEntity<Void> re_save(@RequestBody Tiene tiene){
            tieneServicio.insertarTiene(tiene);
            return new ResponseEntity<>(HttpStatus.OK);
    }
}
