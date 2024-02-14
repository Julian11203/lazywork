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

}
