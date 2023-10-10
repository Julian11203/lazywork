package com.lazywork.repositorio;

import com.lazywork.entidad.InicioSesion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InicioSesionCrudRepository extends CrudRepository<InicioSesion, String> {
}

