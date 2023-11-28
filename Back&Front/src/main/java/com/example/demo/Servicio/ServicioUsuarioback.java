package com.example.demo.Servicio;

import com.example.demo.Entidad.Usuarioback;
import com.example.demo.Repositorio.UsuarioCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ServicioUsuarioback {

    private final UsuarioCrudRepository usuarioRepository;

    @Autowired
    public ServicioUsuarioback(UsuarioCrudRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean existeUsuario(int usuarioId) {
        // Utiliza el repositorio de usuarios para buscar el usuario por su ID.
        Optional<Usuarioback> usuarioOptional = usuarioRepository.findById(Long.valueOf(Long.valueOf(usuarioId)));

        // Verificar si se encontró el usuario
        return usuarioOptional.isPresent();
    }

    public List<Usuarioback> findAll() {
        return usuarioRepository.findAll();
    }
    public Optional<Usuarioback> findById(Long usuarioid) {
        return usuarioRepository.findById(Long.valueOf(usuarioid));
    }


    public Usuarioback save(Usuarioback usuarioBack) {
        return usuarioRepository.save(usuarioBack);
    }

    public void delete(int id) {
        usuarioRepository.deleteById(Long.valueOf(id));
    }

    public Usuarioback actualizarUsuario(int id, Usuarioback usuariobackActualizado) {
        Optional<Usuarioback> usuarioExistente = usuarioRepository.findById(Long.valueOf(Long.valueOf(id)));

        if (usuarioExistente.isPresent()) {
            Usuarioback usuarioBack = usuarioExistente.get();
            // Actualiza los campos necesarios del usuario existente con los valores del usuario actualizado.
            usuarioBack.setNombre(usuariobackActualizado.getNombre());
            usuarioBack.setApellido(usuariobackActualizado.getApellido());
            usuarioBack.setDocumento(usuariobackActualizado.getDocumento());
            usuarioBack.setCorreo(usuariobackActualizado.getCorreo());
            usuarioBack.setTelefono(usuariobackActualizado.getTelefono());
            usuarioBack.setTipoderol(usuariobackActualizado.getTipoderol());
            usuarioBack.setNivelSoporte(usuariobackActualizado.getNivelSoporte());


            // Guarda el usuario actualizado en el repositorio.
            return usuarioRepository.save(usuarioBack);
        } else {
            // Maneja el caso en que no se encuentre el usuario.
            throw new NoSuchElementException("No se encontró el usuario con ID: " + id);
        }
    }

    public boolean existsById(int id) {
        // Lógica para verificar si un usuario existe por su ID
        return usuarioRepository.existsById(Long.valueOf(id));
    }

    public Usuarioback findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }
}
