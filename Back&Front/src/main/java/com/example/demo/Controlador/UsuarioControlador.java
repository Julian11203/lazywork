package com.example.demo.Controlador;

import com.example.demo.Entidad.Usuario;
import com.example.demo.Servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UsuarioControlador {
    @Autowired
    UsuarioServicio usuarioServicio;

    public UsuarioControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }
    @GetMapping
    public ResponseEntity<Usuario> user(@AuthenticationPrincipal OidcUser principal) {
        String roles = String.valueOf(principal.getAuthorities());
        Usuario user = new Usuario(
                (String) principal.getClaims().get("email"),            // correoElectronico
                (String) principal.getClaims().get("name"),             // nombreCompleto
                (String) principal.getClaims().get("picture"),          // fotoPerfil
                (String) principal.getClaims().get("sub"),              // auth_id
                roles                                                   // rolDeUsuario
        );
        usuarioServicio.crear(user); // Con este inserta a la base de datos, sin embargo falta cacharrear como insertar el rol

        // E_Usuario user = this.userServicio.buscarEmail(email);
        return ResponseEntity.ok(user);

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




  /*  @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        // Realiza la revocación del token de acceso en Auth0
        // ...

        // Invalida la sesión y redirige a la página de inicio o donde quieras
        request.getSession().invalidate();
        return "redirect:/index";
    }*/
}
