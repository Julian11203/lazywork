package com.lazywork.estructuraCERS.repositorio;

import com.lazywork.estructuraCERS.entidad.CambioEstado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CambioEstadoCrudRepository extends CrudRepository<CambioEstado, Long> {
}

