package com.lazywork.repositorio;

import com.lazywork.entidad.CambioEstado;
import com.lazywork.entidad.CambioPrioridad;
import org.springframework.data.repository.CrudRepository;

public interface CambioEstadoCrudRepository extends CrudRepository<CambioEstado, String> {
}

