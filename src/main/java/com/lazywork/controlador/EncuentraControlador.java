package com.lazywork.controlador;

import com.lazywork.entidad.Encuentra;
import com.lazywork.servicios.EncuentraServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
