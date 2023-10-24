package com.lazywork.estructuraCERS.servicios;

import com.lazywork.User.User;
import com.lazywork.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UsuarioServicio {
    private static UserRepository usuarioRepository;

    @Autowired
    public UsuarioServicio(UserRepository usuarioRepository) {
        UsuarioServicio.usuarioRepository = usuarioRepository;
    }


    public static boolean existeUsuario(Long usuarioId) {
        // Utiliza el repositorio de usuarios para buscar el usuario por su ID.
        Optional<User> usuarioOptional = usuarioRepository.findById((usuarioId));

        // Verificar si se encontró el usuario
        return usuarioOptional.isPresent();
    }

    public List<User> findAll() {
        return (List<User>) usuarioRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public User save(User usuario) {
        return usuarioRepository.save(usuario);
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }


    public User actualizarUsuario(Long id, User usuarioActualizado) {
        Optional<User> usuarioExistente = usuarioRepository.findById(id);

        if (usuarioExistente.isPresent()) {
            User usuario = usuarioExistente.get();
            // Actualiza los campos necesarios del usuario existente con los valores del usuario actualizado.
            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setApellido(usuarioActualizado.getApellido());
            usuario.setDocumento(usuarioActualizado.getDocumento());
            usuario.setNivelSoporte(usuarioActualizado.getNivelSoporte());

            // Guarda el usuario actualizado en el repositorio.
            return usuarioRepository.save(usuario);
        } else {
            // Maneja el caso en que no se encuentre el usuario.
            throw new NoSuchElementException("No se encontró el usuario con ID: " + id);
        }
    }

    public boolean existsById(Long id) {
        // Lógica para verificar si un usuario existe por su ID
        return usuarioRepository.existsById(id);
    }

    // Puedes agregar más métodos según tus necesidades, como actualización, búsqueda por otros campos, etc.
}