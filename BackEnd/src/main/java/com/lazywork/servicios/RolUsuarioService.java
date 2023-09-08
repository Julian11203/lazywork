package com.lazywork.servicios;

import com.lazywork.entidad.RolUsuario;
import com.lazywork.repositorio.UsuarioRolCrudRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolUsuarioService {


    private UsuarioRolCrudRepository repoRU;
    @Autowired
    public RolUsuarioService(UsuarioRolCrudRepository repoRU) {
        this.repoRU = repoRU;
    }

    public List<RolUsuario> findAll() {
        return (List<RolUsuario>) repoRU.findAll();
    }
    public boolean existsById(Long id){
        return repoRU.existsById(Long.valueOf(id));
    }
    public Optional<RolUsuario> findByID(Long id) {
        if(existsById(id)){
            return Optional.of(repoRU.findById(id).get());
        }else{
            return null;
        }
    }

    public void save(RolUsuario rolUsuario) {
        repoRU.save(rolUsuario);
    }

    public void deleteById(Long id) {
        repoRU.deleteById(Long.valueOf(id));
    }

    // NativeQuery
    public List<RolUsuario> existsByUsuarioId(Long id){
        return repoRU.existsByUsuarioId(id);
    }
}
