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
    private UsuarioRolCrudRepository usuarioRolesRepository;

    public List<UsuarioRol> obtenerTodosLosUsuarioRoles() {
        return (List<UsuarioRol>) usuarioRolesRepository.findAll();
    }

    public Optional<UsuarioRol> obtenerUsuarioRolesPorId(String id) {
        return usuarioRolesRepository.findById(id);
    }

    public UsuarioRol crearUsuarioRoles(UsuarioRol usuarioRoles) {
        return usuarioRolesRepository.save(usuarioRoles);
    }

    public void eliminarUsuarioRoles(String id) {
        usuarioRolesRepository.deleteById(id);
    }
}
