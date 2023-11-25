package com.example.demo.Servicio;

import com.example.demo.Entidad.E_CambioPrioridad;
import com.example.demo.Repositorio.R_CambioPrioridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class S_CambioPrioridad {

    @Autowired
    private R_CambioPrioridad cambioPrioridadRepository;

    public List<E_CambioPrioridad> obtenerTodosLosCambioPrioridad() {
        return (List<E_CambioPrioridad>) cambioPrioridadRepository.findAll();
    }

    public Optional<E_CambioPrioridad> obtenerCambioPrioridadPorId(String id) {
        return cambioPrioridadRepository.findById(id);
    }

    public E_CambioPrioridad crearCambioPrioridad(E_CambioPrioridad cambioPrioridad) {
        return cambioPrioridadRepository.save(cambioPrioridad);
    }

    public void eliminarCambioPrioridad(String id) {
        cambioPrioridadRepository.deleteById(id);
    }
}
