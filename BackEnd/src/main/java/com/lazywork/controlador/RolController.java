package com.lazywork.controlador;



        import com.lazywork.entidad.Rol;
        import com.lazywork.servicios.RolService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.Optional;

@RestController
@RequestMapping("/rol")
public class RolController {

    @Autowired
    private RolService servRol;

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
    public ResponseEntity<Rol> save(@RequestBody Rol rol) {
        if(servRol.existsById(String.valueOf(rol.getRolID())) == false){
            servRol.save(rol);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
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
