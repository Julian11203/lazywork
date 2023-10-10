package com.lazywork.repositorio;

import com.lazywork.entidad.PrioridadIncidencia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrioridadesIncidenciasCrudRepository extends CrudRepository<PrioridadIncidencia, Long> {
}
