package com.lazywork.servicios;

import com.lazywork.entidad.UsuarioSistema;
import com.lazywork.repositorio.UsuarioSistemaCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class UsuarioSistemaService {

    @Autowired
    private UsuarioSistemaCrudRepository usuarioSistemaCrudRepository;

    public boolean existeUsuario(String idUsuario) {
        return usuarioSistemaCrudRepository.existsById(idUsuario);
    }

    public Optional<UsuarioSistema> findById(String id) {
        return usuarioSistemaCrudRepository.findById(id);
    }

    public List<UsuarioSistema> findAll() {
        return (List<UsuarioSistema>) usuarioSistemaCrudRepository.findAll();
    }

    public UsuarioSistema save(UsuarioSistema usuario) {
        try {
            return usuarioSistemaCrudRepository.save(usuario);
        } catch (Exception e) {
            // Maneja la excepción aquí, por ejemplo, puedes registrarla
            throw new RuntimeException("Error al guardar el usuario: " + e.getMessage());
        }
    }

    public void delete(String id) {
        if (existeUsuario(id)) {
            usuarioSistemaCrudRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }
}
