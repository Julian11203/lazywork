package com.example.demo.Controlador;

import com.example.demo.Entidad.Usuario;
import com.example.demo.Servicio.ServicioUsuarioback;
import com.example.demo.Servicio.usuarioServicio;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorUsuario {

    usuarioServicio userServicio;
    ServicioUsuarioback estServicio;

    public ControladorUsuario(ServicioUsuarioback estServicio, usuarioServicio userServicio) {
        this.userServicio = userServicio;
        this.estServicio = estServicio;
    }

    @GetMapping("/user")
    public Usuario usuario(@AuthenticationPrincipal OidcUser principal) {
        System.out.println(principal.getClaims());
        //System.out.println(principal.getUserInfo()); // Cacharrearle
        //System.out.println(principal.getIdToken()); // Cacharrearle
        Usuario user = new Usuario(
                (String) principal.getClaims().get("email"),
                (String) principal.getClaims().get("nickname"),
                (String) principal.getClaims().get("picture"),
                (String) principal.getClaims().get("sub"),
                (String) principal.getClaims().get("rol") // Cacharrearle
        );
        userServicio.crear(user); // Con este inserta a la base de datos, sin embargo falta cacharrear como insertar el rol

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
