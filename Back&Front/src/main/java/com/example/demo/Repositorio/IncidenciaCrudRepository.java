package com.example.demo.Repositorio;

import com.example.demo.Entidad.Incidencia;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidenciaCrudRepository extends CrudRepository<Incidencia, Long> {
        // Métodos de repositorio aquí






        List<Incidencia> findByEstado_EstadoID(Long estadoID);

}


