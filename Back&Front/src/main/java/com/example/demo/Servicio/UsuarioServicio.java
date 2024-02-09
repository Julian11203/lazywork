package com.example.demo.Servicio;

import com.example.demo.Entidad.Usuario;
import com.example.demo.Repositorio.UsuarioCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    public UsuarioServicio(UsuarioCrudRepository usuarioCrudRepository) {
        this.usuarioCrudRepository = usuarioCrudRepository;
    }

    public Usuario crear(Usuario usuario) {
        return usuarioCrudRepository.save(usuario);
    }

    public Usuario buscarDocumento(Long documento) {
        if (usuarioCrudRepository.findById(documento).isPresent()) {
            return usuarioCrudRepository.findById(documento).get();
        } else {
            return null;
        }
    }

    public Usuario getCrearUsuario(Map<String, Object> dataUser) {
        Long documento = (Long) dataUser.get("documento");
        Usuario usuario = buscarDocumento(documento);

        if (usuario == null) {
            String nombre = (String) dataUser.get("nombre");
            String auth_id = (String) dataUser.get("sub");

            // Utiliza repoUsu para obtener Usuarioback
            usuario = usuarioCrudRepository.findByDocumento(documento);

            if (usuario != null) {
                // Asigna la relación y el tipoderol al nuevo usuario
                Usuario nuevo = new Usuario(documento, nombre, auth_id, usuario.getRole());
                return this.crear(nuevo);
            } else {
                // Si no se encuentra Usuarioback, puedes manejarlo según tus necesidades
                return null;
            }
        } else {
            return usuario;
        }
    }
}
