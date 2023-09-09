package com.lazywork.repositorio;

import com.lazywork.entidad.Rol;
import com.lazywork.entidad.UsuarioRol;
import org.springframework.data.repository.CrudRepository;

public interface RolCrudRepository extends CrudRepository<Rol, Long> {
}
