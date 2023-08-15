package com.lazywork.servicios;

import com.lazywork.entidad.Incidencia;
import com.lazywork.entidad.Usuario;
import com.lazywork.repositorio.IncidenciaCrudRepository;
import com.lazywork.repositorio.UsuarioCrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidenciaServicio {
    IncidenciaCrudRepository incidenciaCrudRepository;

    public IncidenciaServicio(IncidenciaCrudRepository incidenciaCrudRepository) {
        this.incidenciaCrudRepository = incidenciaCrudRepository;
    }

    public boolean existeIncidencia(String id){
        return incidenciaCrudRepository.existsById(id);
    }
    public Incidencia incidenciaPorId(String id){
        return incidenciaCrudRepository.findById(id).get();
    }
    public List<Incidencia> listaIncidencias(){
        return (List<Incidencia>) incidenciaCrudRepository.findAll();
    }
}
