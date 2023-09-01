package com.lazywork.servicios;

import com.lazywork.entidad.Incidencia;
import com.lazywork.entidad.Usuario;
import com.lazywork.repositorio.IncidenciaCrudRepository;
import com.lazywork.repositorio.UsuarioCrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidenciaServicio {

    private IncidenciaCrudRepository incidenciaCrudRepository;


    public IncidenciaServicio(IncidenciaCrudRepository incidenciaCrudRepository) {
        this.incidenciaCrudRepository = incidenciaCrudRepository;
    }
    public void save (Incidencia incidencia){
        incidenciaCrudRepository.save(incidencia);
    }
    public void deleteById(String id){
        incidenciaCrudRepository.deleteById(id);
    }
    public boolean existsById(String id){
        return incidenciaCrudRepository.existsById(id);
    }
    public Incidencia findById(String id){
        return incidenciaCrudRepository.findById(id).get();
    }
    public List<Incidencia> findAll(){
        return (List<Incidencia>) incidenciaCrudRepository.findAll();
    }
}