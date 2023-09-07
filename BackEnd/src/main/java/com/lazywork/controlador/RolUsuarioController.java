package com.lazywork.controlador;

import com.lazywork.entidad.RolUsuario;
import com.lazywork.servicios.RolService;
import com.lazywork.servicios.RolUsuarioService;
import com.lazywork.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.RandomAccess;

@RestController
@RequestMapping("/api/rolusuario")
@CrossOrigin("*")
public class RolUsuarioController {

    private RolUsuarioService servRolUsuario;
    private UsuarioService servUsuario;
    private RolService servRol;
    private String mensaje;
    private HttpStatus status;
    private ArrayList respuesta = new ArrayList<>();

    @Autowired
    public RolUsuarioController(RolUsuarioService servRolUsuario, UsuarioService servUsuario, RolService servRol) {
        this.servRolUsuario = servRolUsuario;
        this.servUsuario = servUsuario;
        this.servRol = servRol;
    }




    @GetMapping("/findAll")
    public ResponseEntity<List<RolUsuario>> findAll() {
        respuesta.clear();
        if(!servRolUsuario.findAll().isEmpty()){
            respuesta.add(servRolUsuario.findAll());
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ArrayList> findById(@PathVariable Long id) {
        respuesta.clear();
        if (servRolUsuario.existsById(id)) {
            respuesta.add(servRolUsuario.findByID(id).get());
            status = HttpStatus.OK;
        } else {
            respuesta.add("El rol de usuario con id "+id+" no existe");
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(respuesta, status);
    }


    @PostMapping("/save")
    public ResponseEntity<ArrayList> save(@RequestBody RolUsuario rolUsuario) {
        respuesta.clear();
        if(servRolUsuario.existsById(rolUsuario.getRolUsuarioID()) == false){
            if(servUsuario.existsById(rolUsuario.getUsuario().getUsuarioID())){
                if(servRolUsuario.existsByUsuarioId(rolUsuario.getUsuario().getUsuarioID()).isEmpty()){
                    if(servRol.existsById(rolUsuario.getRol().getRolID())){
                        servRolUsuario.save(rolUsuario);
                        respuesta.add("Se ha creado el rol de usuario exitosamente");
                        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
                    }else{
                        respuesta.add("El rol con id "+rolUsuario.getRol().getRolID()+" no existe");
                        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
                    }
                }else{
                    respuesta.add("No fue posible, el usuario con id "+rolUsuario.getUsuario().getUsuarioID()+" ya esta registrado");
                    return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
                }
            }else{
                respuesta.add("El usuario con id "+rolUsuario.getUsuario().getUsuarioID()+" no existe");
                return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
            }
        }else{
            respuesta.add("El rol de usuario con id "+rolUsuario.getRolUsuarioID()+" ya existe");
            return new ResponseEntity<>(respuesta, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<ArrayList> deleteById(@PathVariable Long id) {
        respuesta.clear();
        if(servRolUsuario.existsById(id)){
            servRolUsuario.deleteById(id);
            mensaje = "El rol de usuario con id "+id+" se ha eliminado correctamente";
            respuesta.add(mensaje);
            status = HttpStatus.OK;
        }else{
            mensaje = "El rol de usuario con id "+id+" no existe";
            respuesta.add(mensaje);
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(respuesta, status);
    }

    @PutMapping("/re_save")
    public ResponseEntity<ArrayList>re_save(@RequestBody RolUsuario rolUsuario){
        respuesta.clear();
        if(servRolUsuario.existsById(rolUsuario.getRolUsuarioID())){
            if(servUsuario.existsById(rolUsuario.getUsuario().getUsuarioID())){
                if(servRolUsuario.existsByUsuarioId(rolUsuario.getUsuario().getUsuarioID()).isEmpty() && rolUsuario.getUsuario().getUsuarioID().equals(servRolUsuario.existsByUsuarioId(rolUsuario.getUsuario().getUsuarioID()))){
                    if(servRol.existsById(rolUsuario.getRol().getRolID())){
                        servRolUsuario.save(rolUsuario);
                        respuesta.add("Se ha actualizado el rol de usuario exitosamente");
                        status = HttpStatus.CREATED;
                    }else{
                        respuesta.add("El rol con id "+rolUsuario.getRol().getRolID()+" no existe");
                        status = HttpStatus.NOT_FOUND;
                    }
                }else{
                    respuesta.add("No fue posible, el usuario con id "+rolUsuario.getUsuario().getUsuarioID()+" ya esta registrado");
                    status = HttpStatus.BAD_REQUEST;
                }
            }else{
                respuesta.add("El usuario con id "+rolUsuario.getUsuario().getUsuarioID()+" no existe");
                status = HttpStatus.NOT_FOUND;
            }
        }else{
            respuesta.add("El rol de usuario con id "+rolUsuario.getRolUsuarioID()+" no existe");
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(respuesta, status);
    }
}
