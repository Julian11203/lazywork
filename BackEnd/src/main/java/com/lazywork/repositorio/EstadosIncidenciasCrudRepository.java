package com.lazywork.repositorio;

import com.lazywork.entidad.EstadoIncidencia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadosIncidenciasCrudRepository extends CrudRepository<EstadoIncidencia, Long> {
}
