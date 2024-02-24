package com.example.demo.Servicio;

import com.example.demo.Entidad.Usuario;
import com.example.demo.Repositorio.UsuarioCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    private final UsuarioCrudRepository usuarioRepository;

    public UsuarioServicio(UsuarioCrudRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public boolean existsByEmail(String email) {
        // Lógica para verificar si un usuario existe por su ID
        return usuarioRepository.existsById(email);
    }


    public Usuario crear(Usuario user) {
        return usuarioRepository.save(user);
    }

    public Optional<Usuario> findOneById(String correoElectronico) {
        return usuarioRepository.findById(correoElectronico);
    }
}
