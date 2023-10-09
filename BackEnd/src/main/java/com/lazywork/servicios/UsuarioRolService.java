package com.lazywork.servicios;

import com.lazywork.entidad.UsuarioRol;
import com.lazywork.repositorio.UsuarioRolCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioRolService {


    private UsuarioRolCrudRepository repoUsuarioRol;
    @Autowired
    public UsuarioRolService(UsuarioRolCrudRepository repoUsuarioRol) {
        this.repoUsuarioRol = repoUsuarioRol;
    }

    public List<UsuarioRol> findAll() {
        return (List<UsuarioRol>) repoUsuarioRol.findAll();
    }

    public Optional<UsuarioRol> findById(String id) {
        return repoUsuarioRol.findById(id);
    }
    public boolean existsById(String id){
        return repoUsuarioRol.existsById(id);
    }

    public UsuarioRol save(UsuarioRol usuarioRoles) {
        return repoUsuarioRol.save(usuarioRoles);
    }

    public void deleteById(String id) {
        repoUsuarioRol.deleteById(id);
    }
    public List<UsuarioRol> existsUsuario(String id){
        return repoUsuarioRol.existsUsuario(id);
    }
}
