package com.lazywork.controlador;



        import com.lazywork.entidad.Rol;
        import com.lazywork.servicios.RolesService;
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
    private RolesService rolesService;

    @GetMapping
    public ResponseEntity<List<Rol>> obtenerTodosLosRoles() {
        List<Rol> roles = rolesService.obtenerTodosLosRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> obtenerRolesPorId(@PathVariable Long id) {
        Optional<Rol> roles = rolesService.obtenerRolesPorId(id);
        if (roles.isPresent()) {
            return ResponseEntity.ok(roles.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Rol> crearRoles(@RequestBody Rol roles) {
        Rol rolesCreado = rolesService.crearRoles(roles);
        return ResponseEntity.status(HttpStatus.CREATED).body(rolesCreado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRoles(@PathVariable Long id) {
        rolesService.eliminarRoles(id);
        return ResponseEntity.noContent().build();
    }
}
