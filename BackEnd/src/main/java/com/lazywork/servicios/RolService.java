package com.lazywork.servicios;

import com.lazywork.entidad.Rol;
import com.lazywork.repositorio.RolCrudRepository;

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

    public List<Rol> findAll() {
        return (List<Rol>) repoRol.findAll();
    }
    public boolean existsById(Long id){
        return repoRol.existsById(String.valueOf(id));
    }
    public Optional<Rol> findById(Long id) {
        return repoRol.findById(String.valueOf(id));
    }

    public Rol save(Rol roles) {
        return repoRol.save(roles);
    }

    public void deleteById(Long id) {
        repoRol.deleteById(String.valueOf(id));
    }
}
