package com.example.demo.Controlador;

import com.example.demo.Servicio.S_Usuarioback;
import com.example.demo.Servicio.S_Usuario;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Usuario {

    S_Usuario userServicio;
    S_Usuarioback estServicio;

    public Usuario(S_Usuarioback estServicio, S_Usuario userServicio) {
        this.userServicio = userServicio;
        this.estServicio = estServicio;
    }

    @GetMapping("/user")
    public com.example.demo.Entidad.Usuario usuario(@AuthenticationPrincipal OidcUser principal) {
            System.out.println(principal.getClaims());
            String email = (String) principal.getClaims().get("email");
            com.example.demo.Entidad.Usuario user = this.userServicio.buscarEmail(email);
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
