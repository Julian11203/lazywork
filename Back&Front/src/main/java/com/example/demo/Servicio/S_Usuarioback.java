package com.example.demo.Servicio;

import com.example.demo.Entidad.E_Usuarioback;
import com.example.demo.Repositorio.R_UsuarioBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class S_Usuarioback {

    private final R_UsuarioBack usuarioRepository;

    @Autowired
    public S_Usuarioback(R_UsuarioBack usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean existeUsuario(int usuarioId) {
        // Utiliza el repositorio de usuarios para buscar el usuario por su ID.
        Optional<E_Usuarioback> usuarioOptional = usuarioRepository.findById(Long.valueOf(Long.valueOf(usuarioId)));

        // Verificar si se encontró el usuario
        return usuarioOptional.isPresent();
    }

    public List<E_Usuarioback> findAll() {
        return usuarioRepository.findAll();
    }
    public Optional<E_Usuarioback> findById(Long usuarioid) {
        return usuarioRepository.findById(Long.valueOf(usuarioid));
    }


    public E_Usuarioback save(E_Usuarioback usuarioBack) {
        return usuarioRepository.save(usuarioBack);
    }

    public void delete(int id) {
        usuarioRepository.deleteById(Long.valueOf(id));
    }

    public E_Usuarioback actualizarUsuario(int id, E_Usuarioback usuariobackActualizado) {
        Optional<E_Usuarioback> usuarioExistente = usuarioRepository.findById(Long.valueOf(Long.valueOf(id)));

        if (usuarioExistente.isPresent()) {
            E_Usuarioback usuarioBack = usuarioExistente.get();
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

    // Puedes agregar más métodos según tus necesidades, como actualización, búsqueda por otros campos, etc.
}
