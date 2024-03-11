package com.example.demo.Controlador;

import com.example.demo.Entidad.Usuario;
import com.example.demo.Servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inicio")
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
            String rolActual = "USER"; // Rol por defecto
            Boolean roleUser = false;
            Boolean roleAdmin = false;
            if(usuarioServicio.existsByEmail(principal.getEmail())){
                // Obtiene el rol del usuario
                if(principal.getClaims().containsKey("${namespace}/roles")) {
                    Object rolesObject = principal.getClaims().get("${namespace}/roles");
                    if (rolesObject instanceof List) {
                        List<String> roles = (List<String>) rolesObject;
                        roleAdmin = roles.contains("ADMIN");
                        roleUser = roles.contains("USER");
                    }
                }
                rolActual = usuarioServicio.findOneById(principal.getEmail()).get().getRolActual();
            }
            Usuario user = new Usuario(
                    // Los datos los trae de Auth0 y los almacena en la BD
                    (String) principal.getClaims().get("email"),            // correoElectronico
                    (String) principal.getClaims().get("name"),             // nombreCompleto
                    rolActual,
                    roleUser,                                                    // roleUser
                    roleAdmin,                                                    // roleAdmin
                    principal.getAccessTokenHash()
            );
            usuarioServicio.crear(user);
            return ResponseEntity.ok(user);
        }
        else{
            return null;
        }
    }



    @GetMapping("/{correoElectronico}")
    public ResponseEntity<Optional<Usuario>> findOneById(@PathVariable String correoElectronico) {
        if(usuarioServicio.existsByEmail(correoElectronico)){
            return ResponseEntity.ok(usuarioServicio.findOneById(correoElectronico));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


}
