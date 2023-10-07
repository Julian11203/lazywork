package com.lazywork.repositorio;

import com.lazywork.entidad.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioCrudRepository extends CrudRepository<Usuario, Long> {
}
