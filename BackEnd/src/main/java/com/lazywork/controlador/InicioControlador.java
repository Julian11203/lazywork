package com.lazywork.controlador;

import com.lazywork.entidad.Inicio;
import com.lazywork.servicios.InicioServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inicio")
public class InicioControlador {
    InicioServicio inicioServicio;
    public InicioControlador(InicioServicio inicioServicio) {
        this.inicioServicio = inicioServicio;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Inicio> inicioPorId(@PathVariable String id){
        if(inicioServicio.existeInicio(id)){
            return new ResponseEntity<>(inicioServicio.inicioPorId(id), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Inicio>> listaInicios(){
        return new ResponseEntity<>(inicioServicio.listaInicios(), HttpStatus.OK);
    }
}
