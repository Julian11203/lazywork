package com.lazywork.servicios;


import com.lazywork.entidad.CambioPrioridad;
import com.lazywork.repositorio.CambioPrioridadCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CambioPrioridadService {

    @Autowired
    private CambioPrioridadCrudRepository cambioPrioridadRepository;

    public List<CambioPrioridad> obtenerTodosLosCambioPrioridad() {
        return (List<CambioPrioridad>) cambioPrioridadRepository.findAll();
    }

    public Optional<CambioPrioridad> obtenerCambioPrioridadPorId(String id) {
        return cambioPrioridadRepository.findById(id);
    }

    public CambioPrioridad crearCambioPrioridad(CambioPrioridad cambioPrioridad) {
        return cambioPrioridadRepository.save(cambioPrioridad);
    }

    public void eliminarCambioPrioridad(String id) {
        cambioPrioridadRepository.deleteById(id);
    }
}
