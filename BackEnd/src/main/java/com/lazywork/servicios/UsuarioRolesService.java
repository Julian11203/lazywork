package com.lazywork.servicios;

import com.lazywork.entidad.UsuarioRol;
import com.lazywork.repositorio.UsuarioRolCrudRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioRolesService {

    @Autowired
    private UsuarioRolCrudRepository repoUsuarioRol;

    public List<UsuarioRol> findAll() {
        return (List<UsuarioRol>) repoUsuarioRol.findAll();
    }

    public Optional<UsuarioRol> findById(Long id) {
        return repoUsuarioRol.findById(id);
    }
    public boolean existsByID(Long id){
        return repoUsuarioRol.existsById(id);
    }

    public UsuarioRol save(UsuarioRol usuarioRoles) {
        return repoUsuarioRol.save(usuarioRoles);
    }

    public void deleteById(Long id) {
        repoUsuarioRol.deleteById(id);
    }
}
