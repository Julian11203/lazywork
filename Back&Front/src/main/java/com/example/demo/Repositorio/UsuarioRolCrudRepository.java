package com.example.demo.Repositorio;

import com.example.demo.Entidad.UsuarioRol;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRolCrudRepository extends CrudRepository<UsuarioRol, String> {
    @Query(value = "SELECT * FROM usuario_rol ru WHERE ru.usuarioid = :usuarioid", nativeQuery = true)
    List<UsuarioRol> existsUsuario(@Param("usuarioid") String id);
}
