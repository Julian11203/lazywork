package com.lazywork.controlador;



        import com.lazywork.entidad.Rol;
        import com.lazywork.entidad.Usuario;
        import com.lazywork.servicios.RolService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Optional;

@RestController
@RequestMapping("/api/rol")
public class RolController {
    private RolService servicioR;
    private HttpStatus status;
    private ArrayList respuesta = new ArrayList<>();
    @Autowired
    public RolController(RolService servicioRol) {
        this.servicioR = servicioRol;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Rol>> findAll() {
        respuesta.clear();
        status = HttpStatus.OK;
        if(!servicioR.findAll().isEmpty()){
            respuesta.add(servicioR.findAll());
            return new ResponseEntity<>(respuesta, status);
        }else{
            return new ResponseEntity<>(status);
        }

    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ArrayList> findById(@PathVariable Long id) {
        respuesta.clear();
        if (servicioR.existsById(Long.valueOf(id))) {
            respuesta.add(servicioR.findById(id).get());
            status = HttpStatus.OK;
        } else {
            respuesta.add("No se encontro el rol");
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(respuesta, status);
    }

    @PostMapping("/save")
    public ResponseEntity<ArrayList> save(@RequestBody Rol rol) {
        respuesta.clear();
        if(servicioR.existsById(rol.getRolID()) == false){
            servicioR.save(rol);
            respuesta.add("Se ha creado el rol exitosamente");
            status = HttpStatus.CREATED;

        }
        else{
            respuesta.add("El rol ya existe");
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(respuesta, status);
    }


    @PutMapping("/re_save")
    public ResponseEntity<ArrayList> re_save(@RequestBody Rol rol) {
        respuesta.clear();
        if(servicioR.existsById(rol.getRolID())){
            servicioR.save(rol);
            respuesta.add("Se ha actualizado correctamente");
            status = HttpStatus.OK;
        }
        else{
            respuesta.add("El rol a actualizar no existe");
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(respuesta, status);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<ArrayList> deleteById(@PathVariable Long id) {
        respuesta.clear();
        if(servicioR.existsById(Long.valueOf(id))){
            servicioR.deleteById(id);
            respuesta.add("Se elimino correctamente");
            status = HttpStatus.OK;
        }
        else{
            respuesta.add("Imposible eliminar, el rol no existe");
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(respuesta, status);
    }
}
