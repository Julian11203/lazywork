package com.lazywork.repositorio;

import com.lazywork.entidad.Rol;
import com.lazywork.entidad.RolUsuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRolCrudRepository extends CrudRepository<RolUsuario, Long> {
    @Query(value = "SELECT * FROM rol_usuario ru WHERE ru.usuarioid = :usuarioid", nativeQuery = true)
    List<RolUsuario> existsByUsuarioId(@Param("usuarioid") Long id);
}
