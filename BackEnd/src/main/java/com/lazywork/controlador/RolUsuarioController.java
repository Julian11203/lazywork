package com.lazywork.controlador;

import com.lazywork.entidad.RolUsuario;
import com.lazywork.servicios.RolService;
import com.lazywork.servicios.RolUsuarioService;
import com.lazywork.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/rolusuario")
@CrossOrigin("*")
public class RolUsuarioController {

    private RolUsuarioService servRolUsuario;
    private UsuarioServicio servUsuario;
    private RolService servRol;
    private String mensaje;
    private HttpStatus status;
    private ArrayList respuesta = new ArrayList<>();

    @Autowired
    public RolUsuarioController(RolUsuarioService servRolUsuario, UsuarioServicio servUsuario, RolService servRol) {
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
    public ResponseEntity<String> save(@RequestBody RolUsuario rolUsuario) {
        Long usuarioId = rolUsuario.getUsuario().getId();
        Long rolId = rolUsuario.getRol().getRolID();

        if (servRolUsuario.existsById(rolUsuario.getRolUsuarioID())) {
            return new ResponseEntity<>("El rol de usuario con id " + rolUsuario.getRolUsuarioID() + " ya existe", HttpStatus.CONFLICT);
        }

        if (!servUsuario.existsById(String.valueOf(usuarioId))) {
            return new ResponseEntity<>("El usuario con id " + usuarioId + " no existe", HttpStatus.NOT_FOUND);
        }

        if (!servRol.existsById(rolId)) {
            return new ResponseEntity<>("El rol con id " + rolId + " no existe", HttpStatus.NOT_FOUND);
        }

        if (servRolUsuario.existsByUsuarioId(usuarioId)) {
            return new ResponseEntity<>("No fue posible, el usuario con id " + usuarioId + " ya está registrado", HttpStatus.BAD_REQUEST);
        }

        servRolUsuario.save(rolUsuario);
        return new ResponseEntity<>("Se ha creado el rol de usuario exitosamente", HttpStatus.CREATED);
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
}
