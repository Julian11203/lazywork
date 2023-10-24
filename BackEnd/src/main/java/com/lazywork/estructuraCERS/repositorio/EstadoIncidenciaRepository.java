package com.lazywork.estructuraCERS.repositorio;

import com.lazywork.estructuraCERS.entidad.EstadoIncidencia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoIncidenciaRepository extends CrudRepository<EstadoIncidencia, Long> {
}
