package com.lazywork.estructuraCERS.repositorio;

import com.lazywork.estructuraCERS.entidad.Incidencia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidenciaRepository extends CrudRepository<Incidencia, Long> {
}