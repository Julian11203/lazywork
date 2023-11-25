package com.example.demo.Servicio;

import com.example.demo.Entidad.Usuario;
import com.example.demo.Repositorio.R_UsuarioBack;
import com.example.demo.Repositorio.R_Usuario;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class S_Usuario {

    private R_Usuario repositorio;
    private R_UsuarioBack repoEst;

    public S_Usuario(R_Usuario repositorio, R_UsuarioBack repoEst) {
        this.repositorio = repositorio;
        this.repoEst = repoEst;
    }

    public Usuario crear(Usuario usuario) {
        return repositorio.save(usuario);
    }

    public Usuario buscarEmail(String email) {
        if (repositorio.findById(email).isPresent()) {
            return repositorio.findById(email).get();
        } else {
            return null;
        }
    }

    public Usuario getCrearUsuario(Map<String, Object> dataUser) {
        String email = (String) dataUser.get("email");
        Usuario user = buscarEmail(email); // Si ya existe, solo lo retorna
        String rol = "";
        if (user == null) {
            String name = (String) dataUser.get("nickname");
            String imag = (String) dataUser.get("picture");
            String auth_id = (String) dataUser.get("sub");
            // Estudiante est = repoEst.findByCorreo(email);
            if (repoEst.findByCorreo(email) != null) {
                rol = "E_Usuarioback";
            } else {
                rol = "Otro";
            }
            Usuario nuevo = new Usuario(email, name, imag, auth_id, rol);
            return this.crear(nuevo);
        } else {
            return user;
        }
    }
}
