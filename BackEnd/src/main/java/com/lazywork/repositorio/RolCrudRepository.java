package com.lazywork.repositorio;

import com.lazywork.entidad.Rol;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RolCrudRepository extends CrudRepository<Rol, String> {
    @Query("SELECT CASE WHEN COUNT(ur) > 0 THEN true ELSE false END FROM UsuarioRol ur INNER JOIN " +
            "ur.rol rol WHERE rol.id = :rolId")
    boolean existsInUsuarioRol(@Param("rolId") String rolId);
}
