package com.lazywork.repositorio;

import com.lazywork.entidad.Incidencia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidenciaCrudRepository extends CrudRepository<Incidencia, Long> {
}
