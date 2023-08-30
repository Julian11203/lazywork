package com.lazywork.controlador;

import com.lazywork.entidad.Inicio;
import com.lazywork.servicios.InicioServicio;
import com.lazywork.servicios.UsuarioServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inicio")
public class InicioControlador {
    private UsuarioServicio usuarioServicio;
    private InicioServicio inicioServicio;

    public InicioControlador(UsuarioServicio usuarioServicio, InicioServicio inicioServicio) {
        this.usuarioServicio = usuarioServicio;
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
    @PostMapping("/insertar")
    public ResponseEntity<Inicio> insertarInicio(@RequestBody Inicio inicio){
        return new ResponseEntity<>(inicioServicio.insertarInicio(inicio), HttpStatus.CREATED);
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarInicio(@PathVariable String id){
        if(inicioServicio.existeInicio(id)){
            inicioServicio.eliminarInicio(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
