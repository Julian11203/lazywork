package com.lazywork.estructuraCERS.repositorio;

import com.lazywork.estructuraCERS.entidad.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioCrudRepository extends CrudRepository<Usuario, Long> {
}
