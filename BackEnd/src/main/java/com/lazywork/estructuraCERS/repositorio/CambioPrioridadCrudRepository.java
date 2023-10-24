package com.lazywork.estructuraCERS.repositorio;

import com.lazywork.estructuraCERS.entidad.CambioPrioridad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CambioPrioridadCrudRepository extends CrudRepository<CambioPrioridad, String> {
}

