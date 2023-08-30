package com.lazywork.servicios;

import com.lazywork.entidad.Prioridad;
import com.lazywork.repositorio.PrioridadCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrioridadServicio {
    @Autowired
    PrioridadCrudRepository prioridadCrudRepository;
    public Optional<Prioridad> findById(String id){
        return prioridadCrudRepository.findById(id);
    }
    public List<Prioridad> findAll(){
        return (List<Prioridad>) prioridadCrudRepository.findAll();
    }
    public void save(Prioridad prioridad){
        prioridadCrudRepository.save(prioridad);
    }
    public void delete(String id){
        prioridadCrudRepository.deleteById(id);
    }



}
