package com.example.demo.Controlador;
import com.example.demo.Entidad.Rol;
import com.example.demo.Servicio.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rol")
@CrossOrigin("*")
public class RolController {

    @Autowired
    private RolService servRol;

    public RolController(RolService servRol) {
        this.servRol = servRol;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Rol>> findAll() {
        if(servRol.finAll().isEmpty()){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.ok(servRol.finAll());
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Rol> findById(@PathVariable String id) {
        Optional<Rol> rol = servRol.findById(id);
        if (rol.isPresent()) {
            return ResponseEntity.ok(rol.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody Rol rol) {
        if (servRol.existsById(rol.getRolID())) {
            // El rol con el mismo rolID ya existe, devuelve un estado de conflicto
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            // El rol no existe, guárdalo en la base de datos
            servRol.save(rol);
            // Devuelve una respuesta exitosa con HTTP 201 Created
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @PutMapping("/re_save")
    public ResponseEntity<Rol> re_save(@RequestBody Rol rol) {
        if(servRol.existsById(String.valueOf(rol.getRolID()))){
            servRol.save(rol);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        if(servRol.existsById(id)){
            if(servRol.existsInUsuarioRol(id)){
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }else{
                servRol.deleteById(id);
                return ResponseEntity.ok().build();
            }
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}