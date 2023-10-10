package com.lazywork.repositorio;

import com.lazywork.entidad.CambioPrioridad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CambioPrioridadCrudRepository extends CrudRepository<CambioPrioridad, String> {
}

