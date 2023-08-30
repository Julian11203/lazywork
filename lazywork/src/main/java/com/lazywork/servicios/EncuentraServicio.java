package com.lazywork.servicios;

import com.lazywork.entidad.Encuentra;
import com.lazywork.repositorio.EncuentraCrudRepository;
import com.lazywork.repositorio.EstadoCrudRepository;
import com.lazywork.repositorio.IncidenciaCrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncuentraServicio {
    private EncuentraCrudRepository encuentraCrudRepository;
    private EstadoCrudRepository estadoCrudRepository;
    private IncidenciaCrudRepository incidenciaCrudRepository;

    public EncuentraServicio(EncuentraCrudRepository encuentraCrudRepository, EstadoCrudRepository estadoCrudRepository, IncidenciaCrudRepository incidenciaCrudRepository) {
        this.encuentraCrudRepository = encuentraCrudRepository;
        this.estadoCrudRepository = estadoCrudRepository;
        this.incidenciaCrudRepository = incidenciaCrudRepository;
    }

    public boolean existeEncuentra(String id){
        return encuentraCrudRepository.existsById(id);
    }
    public List<Encuentra> listarEncuentra(){
        return (List<Encuentra>) encuentraCrudRepository.findAll();
    }
    public Encuentra encuentraPorId(String id){
        return encuentraCrudRepository.findById(id).get();
    }

    public Encuentra insertarEncuentra(Encuentra encuentra){
        encuentra.setEstado(estadoCrudRepository.findById(encuentra.getEstado().getIdEstado()).get());
        encuentra.setIncidencia(incidenciaCrudRepository.findById(encuentra.getIncidencia().getNoIncidencia()).get());
        return encuentraCrudRepository.save(encuentra);
    }

    public void eliminarEncuentra(String id){
        encuentraCrudRepository.deleteById(id);
    }
}
