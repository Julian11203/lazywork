package com.lazywork.estructuraCERS.repositorio;

import com.lazywork.estructuraCERS.entidad.PrioridadIncidencia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrioridadesIncidenciasCrudRepository extends CrudRepository<PrioridadIncidencia, Long> {
}
