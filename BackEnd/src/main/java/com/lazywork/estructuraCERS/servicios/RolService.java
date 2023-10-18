package com.lazywork.estructuraCERS.servicios;

import com.lazywork.estructuraCERS.entidad.Rol;
import com.lazywork.estructuraCERS.repositorio.RolCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {


    private RolCrudRepository repoRol;
    @Autowired
    public RolService(RolCrudRepository repoRol) {
        this.repoRol = repoRol;
    }

    public List<Rol> finAll() {
        return (List<Rol>) repoRol.findAll();
    }

    public boolean existsById(String id){
        return repoRol.existsById(id);
    }
    public Optional<Rol> findById(String id) {
        return repoRol.findById(id);
    }

    public Rol save(Rol roles) {
        return repoRol.save(roles);
    }

    public void deleteById(String id) {
        repoRol.deleteById(id);
    }

    public boolean existsInUsuarioRol(String id){
        return repoRol.existsInUsuarioRol(id);
    }
}
