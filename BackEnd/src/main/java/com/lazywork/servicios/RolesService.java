package com.lazywork.servicios;

import com.lazywork.entidad.Rol;
import com.lazywork.repositorio.RolCrudRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesService {

    @Autowired
    private RolCrudRepository rolesRepository;

    public List<Rol> obtenerTodosLosRoles() {
        return (List<Rol>) rolesRepository.findAll();
    }

    public Optional<Rol> obtenerRolesPorId(Long id) {
        return rolesRepository.findById(id);
    }

    public Rol crearRoles(Rol roles) {
        return rolesRepository.save(roles);
    }

    public void eliminarRoles(Long id) {
        rolesRepository.deleteById(id);
    }
}
