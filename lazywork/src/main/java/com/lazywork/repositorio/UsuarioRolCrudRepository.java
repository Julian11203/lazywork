package com.lazywork.repositorio;

import com.lazywork.entidad.UsuarioRol;
import com.lazywork.entidad.UsuarioSistema;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRolCrudRepository extends CrudRepository<UsuarioRol, String> {
}
