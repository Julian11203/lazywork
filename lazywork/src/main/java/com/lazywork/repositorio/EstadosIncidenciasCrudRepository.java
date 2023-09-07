package com.lazywork.repositorio;

import com.lazywork.entidad.EstadoIncidencia;
import com.lazywork.entidad.UsuarioSistema;
import org.springframework.data.repository.CrudRepository;

public interface EstadosIncidenciasCrudRepository extends CrudRepository<EstadoIncidencia, String> {
}
