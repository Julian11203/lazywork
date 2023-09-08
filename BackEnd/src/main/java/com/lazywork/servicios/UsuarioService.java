package com.lazywork.servicios;

import com.lazywork.entidad.Usuario;
import com.lazywork.repositorio.UsuarioCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioCrudRepository repoUsuario;

    public boolean existsById(Long id) {
        return repoUsuario.existsById(String.valueOf(id));
    }

    public Optional<Usuario> findById(String id) {
        return repoUsuario.findById(id);
    }

    public List<Usuario> findAll() {
        return (List<Usuario>) repoUsuario.findAll();
    }

    public Usuario save(Usuario usuario) {
        try {
            return repoUsuario.save(usuario);
        } catch (Exception e) {
            // Maneja la excepción aquí, por ejemplo, puedes registrarla
            throw new RuntimeException("Error al guardar el usuario: " + e.getMessage());
        }
    }

    public void deleteById(Long id) {
        repoUsuario.deleteById(String.valueOf(id));
    }
}
