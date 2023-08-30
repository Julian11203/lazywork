package com.lazywork.repositorio;

import com.lazywork.entidad.Estado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoCrudRepository extends CrudRepository<Estado, String> {
    @Query("SELECT e FROM Estado e WHERE e.tipoEstado = :tipoEstado")
    Estado findByTipoEstado(@Param("tipoEstado") String tipoEstado);
}