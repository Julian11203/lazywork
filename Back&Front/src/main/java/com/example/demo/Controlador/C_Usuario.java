package com.example.demo.Controlador;

import com.example.demo.Entidad.E_Usuario;
import com.example.demo.Servicio.S_Usuarioback;
import com.example.demo.Servicio.S_Usuario;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class C_Usuario {

    S_Usuario userServicio;
    S_Usuarioback estServicio;

    public C_Usuario(S_Usuario userServicio, S_Usuarioback estServicio) {
        this.userServicio = userServicio;
        this.estServicio = estServicio;
    }

    @GetMapping("/user")
    public E_Usuario usuario(@AuthenticationPrincipal OidcUser principal) {
        System.out.println(principal.getClaims());
        String email = (String) principal.getClaims().get("email");
        E_Usuario user = this.userServicio.buscarEmail(email);
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
