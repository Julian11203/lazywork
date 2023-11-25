package com.example.demo.Controlador;

import com.example.demo.Entidad.Usuario;
import com.example.demo.Servicio.S_Usuarioback;
import com.example.demo.Servicio.S_Usuario;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class Inicio {

    S_Usuario userServicio;
    S_Usuarioback estServicio;

    public Inicio(S_Usuarioback estServicio, S_Usuario userServicio) {
        this.userServicio = userServicio;
        this.estServicio = estServicio;
    }

    @GetMapping("/inicio") //Ruta Raiz
    public String index(Model model, @AuthenticationPrincipal OidcUser principal) {

        if (principal != null) {
            System.out.println(principal.getClaims());
            //Usuario user = this.userServicio.getCrearUsuario(principal.getClaims().get("email")); //trae el correo de auth0
            Usuario user = this.userServicio.getCrearUsuario(principal.getClaims());
            model.addAttribute("user",user);
            if(user.getRol().equals("E_Usuarioback")){ //Consultar que rol es y redirige a la interfaz de ese usuario
                return "redirect:/GestionAdmin.html";
            }else{
                return "redirect:/GestionUser.html";
            }
        }
        else{
            return "redirect:/";
        }
            //System.out.println(principal.getClaims());//Trae informacion del usuario de inicio de sesio

    }
}
