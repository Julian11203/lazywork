package com.lazywork.servicios;

import com.lazywork.entidad.RolUsuario;
import com.lazywork.repositorio.UsuarioRolCrudRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class RolUsuarioService {

    private final UsuarioRolCrudRepository repoRU;

    @Autowired
    public RolUsuarioService(UsuarioRolCrudRepository repoRU) {
        this.repoRU = repoRU;
    }

    public List<RolUsuario> findAll() {
        return (List<RolUsuario>) repoRU.findAll();
    }

    public boolean existsById(Long id) {
        return repoRU.existsById(id);
    }

    public Optional<RolUsuario> findByID(Long id) {
        return repoRU.findById(id);
    }

    public void save(RolUsuario rolUsuario) {
        repoRU.save(rolUsuario);
    }

    public void deleteById(Long id) {
        repoRU.deleteById(id);
    }

    // NativeQuery
    public boolean existsByUsuarioId(Long id) {
        List<RolUsuario> rolUsuarios = repoRU.existsByUsuarioId(id);
        return !rolUsuarios.isEmpty();
    }
}
