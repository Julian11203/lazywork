package com.example.demo.Controlador;

import com.example.demo.Entidad.Usuario;
import com.example.demo.Servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        //System.out.println(principal.getUserInfo()); // Cacharrearle
        //System.out.println(principal.getIdToken()); // Cacharrearle
        Usuario user = new Usuario(
                (String) principal.getClaims().get("name"),
                (String) principal.getClaims().get("user_id")
        );

        // E_Usuario user = this.userServicio.buscarEmail(email);
        return user;
    }
    @PostMapping("/registro")
    public String registrarUsuarioDespuesAutenticacion(@AuthenticationPrincipal OAuth2User oauth2User) {
        // Extraer información del usuario autenticado
        String user_id = oauth2User.getAttribute("user_id");
        String name = oauth2User.getAttribute("name");

        // Crear un nuevo usuario en la base de datos
        Usuario usuario = new Usuario();
        usuario.setUser_id(user_id);
        usuario.setName(name);
        usuarioServicio.crear(usuario);

        return "redirect:/index";
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
