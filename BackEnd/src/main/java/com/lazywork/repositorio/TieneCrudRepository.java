package com.lazywork.repositorio;

import com.lazywork.entidad.Tiene;
import org.springframework.data.repository.CrudRepository;

public interface TieneCrudRepository  extends CrudRepository<Tiene, String> {
}
