package com.example.demo.Controlador;

import com.example.demo.Entidad.Usuario;
import com.example.demo.Servicio.UsuarioServicio;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UsuarioControlador {
    @Autowired
    private final UsuarioServicio usuarioServicio;

    public UsuarioControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    // Esta funcion crea y mantiene actualizada la base de datos con la informacion de Auth0
    // No es posible dejar el ROLE en esta funcion ya que cambiaria los ROLES de los usuarios continuamente
    @GetMapping
    public ResponseEntity<Usuario> persistenciaDeDatosDeOAuth0ABaseDeDatos(@AuthenticationPrincipal OidcUser principal) {
        if(principal != null){
            String role = "USER"; // Rol por defecto
            if(usuarioServicio.existsByEmail(principal.getEmail())){
                // Obtiene el rol del usuario
                role = usuarioServicio.findOneById(principal.getEmail()).get().getRolDeUsuario();
            }
            Usuario user = new Usuario(
                    // Los datos los trae de Auth0 y los almacena en la BD
                    (String) principal.getClaims().get("email"),            // correoElectronico
                    (String) principal.getClaims().get("name"),             // nombreCompleto
                    role                                                    // rolDeUsuario
            );
            usuarioServicio.crear(user);
            return ResponseEntity.ok(user);
        }
        else{
            return null;
        }
    }

    @DeleteMapping("/{correoElectronico}")
    public ResponseEntity<String> deleteById(@PathVariable String correoElectronico) {
        
        return null;
    }


    @GetMapping("/{correoElectronico}")
    public ResponseEntity<Optional<Usuario>> findById(@PathVariable String correoElectronico) {
        return ResponseEntity.ok(usuarioServicio.findOneById(correoElectronico));
    }
}
