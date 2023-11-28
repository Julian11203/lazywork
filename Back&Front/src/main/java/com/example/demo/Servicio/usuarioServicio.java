package com.example.demo.Servicio;

import com.example.demo.Entidad.Usuario;
import com.example.demo.Entidad.Usuarioback;
import com.example.demo.Repositorio.UsuarioCrudRepository;
import com.example.demo.Repositorio.usuarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class usuarioServicio {

    private usuarioRepositorio repositorio;
    private UsuarioCrudRepository repoUsu;

    public usuarioServicio(usuarioRepositorio repositorio, UsuarioCrudRepository repoUsu) {
        this.repositorio = repositorio;
        this.repoUsu = repoUsu;
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
        Usuario user = buscarEmail(email);

        if (user == null) {
            String name = (String) dataUser.get("nickname");
            String imag = (String) dataUser.get("picture");
            String auth_id = (String) dataUser.get("sub");

            // Utiliza repoUsu para obtener Usuarioback
            Usuarioback usuarioback = repoUsu.findByCorreo(email);

            if (usuarioback != null) {
                // Asigna la relación y el tipoderol al nuevo usuario
                Usuario nuevo = new Usuario(email, name, imag, auth_id, usuarioback, usuarioback.getTipoderol());
                return this.crear(nuevo);
            } else {
                // Si no se encuentra Usuarioback, puedes manejarlo según tus necesidades
                return null;
            }
        } else {
            return user;
        }
    }
}
