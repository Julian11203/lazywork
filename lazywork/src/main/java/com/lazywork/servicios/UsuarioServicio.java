package com.lazywork.servicios;

import com.lazywork.entidad.Usuario;
import com.lazywork.repositorio.UsuarioCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    public UsuarioServicio(UsuarioCrudRepository usuarioCrudRepository) {
        this.usuarioCrudRepository = usuarioCrudRepository;
    }
    public boolean existeUsuario(String idUser){
        return usuarioCrudRepository.existsById(idUser);
    }

    public  Optional<Usuario> findById(String id){
        return  usuarioCrudRepository.findById(id);
    }

    public List<Usuario> findall(){
        return (List<Usuario>) usuarioCrudRepository.findAll();
    }

    public void save(Usuario usuario){
        usuarioCrudRepository.save(usuario);
    }

    public void delete(String id){
        usuarioCrudRepository.deleteById(id);
    }

}