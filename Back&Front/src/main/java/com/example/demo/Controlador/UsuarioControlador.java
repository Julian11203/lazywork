package com.example.demo.Controlador;

import com.example.demo.Entidad.Usuario;
import com.example.demo.Servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    public UsuarioControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping("/user")
    public Usuario usuario(@AuthenticationPrincipal OidcUser principal) {
        System.out.println(principal.getClaims());
        //System.out.println(principal.getUserInfo()); // Cacharrearle
        //System.out.println(principal.getIdToken()); // Cacharrearle
        Usuario user = new Usuario(
                (Long) principal.getClaims().get("documento"),
                (String) principal.getClaims().get("nombre"),
                (String) principal.getClaims().get("auth_id"),
                (String) principal.getClaims().get("role")
        );
        usuarioServicio.crear(user); // Con este inserta a la base de datos, sin embargo falta cacharrear como insertar el rol

        // E_Usuario user = this.userServicio.buscarEmail(email);
        return user;

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
