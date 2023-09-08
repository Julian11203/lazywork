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
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolService servicioR;

    public RolController(RolService servicioRol) {
        this.servicioR = servicioRol;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Rol>> findAll() {
        return new ResponseEntity<>(servicioR.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<Rol>> findById(@PathVariable Long id) {
        if (servicioR.existsById(id)) {
            return new ResponseEntity<>(servicioR.findById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Rol> save(@RequestBody Rol rol) {
        if(servicioR.existsById(rol.getRolID()) == false){
            servicioR.save(rol);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


    @PutMapping("/re_save")
    public ResponseEntity<Rol> re_save(@RequestBody Rol rol) {
        if(servicioR.existsById(rol.getRolID())){
            servicioR.save(rol);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if(servicioR.existsById(id)){
            servicioR.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
