package com.example.demo.Repositorio;

import com.example.demo.Entidad.E_Incidencia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface R_Incidencia extends CrudRepository<E_Incidencia, Long> {
        // Métodos de repositorio aquí






        List<E_Incidencia> findByEstado_EstadoID(Long estadoID);

}


