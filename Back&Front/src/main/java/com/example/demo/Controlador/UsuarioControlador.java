package com.example.demo.Controlador;

import com.example.demo.Entidad.Usuario;
import com.example.demo.Servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UsuarioControlador {
    @Autowired
    UsuarioServicio usuarioServicio;
    public UsuarioControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    // Esta funcion crea y mantiene actualizada la base de datos con la informacion de Auth0
    // No es posible dejar el ROLE en esta funcion ya que cambiaria los ROLES de los usuarios continuamente
    @GetMapping
    public ResponseEntity<Usuario> persistenciaDeDatosDeOAuth0ABaseDeDatos(@AuthenticationPrincipal OidcUser principal) {
        if(principal != null){
            String role = "USER";
            Usuario user = new Usuario(
                    // Los datos los trae de Auth0 y los almacena en la BD
                    (String) principal.getClaims().get("email"),            // correoElectronico
                    (String) principal.getClaims().get("name"),             // nombreCompleto
                    role                                                    // rolDeUsuario
            );
            usuarioServicio.crear(user);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.ok().build();
    }




    @GetMapping("/{correoElectronico}")
    public ResponseEntity<Optional<Usuario>> loadUserInfo(@PathVariable String correoElectronico) {
        if(usuarioServicio.existsByEmail(correoElectronico)){
            return ResponseEntity.ok(usuarioServicio.findOneById(correoElectronico));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }








}
