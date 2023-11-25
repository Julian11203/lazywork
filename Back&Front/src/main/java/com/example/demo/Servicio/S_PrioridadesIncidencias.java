package com.example.demo.Servicio;

import com.example.demo.Entidad.E_PrioridadIncidencia;
import com.example.demo.Repositorio.R_PrioridadesIncidencias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class S_PrioridadesIncidencias {

    @Autowired
    private R_PrioridadesIncidencias prioridadesIncidenciasCrudRepository;

    public List<E_PrioridadIncidencia> obtenerTodasLasPrioridadesIncidencias() {
        return (List<E_PrioridadIncidencia>) prioridadesIncidenciasCrudRepository.findAll();
    }

    public Optional<E_PrioridadIncidencia> obtenerPrioridadesIncidenciasPorId(Long id) {
        return prioridadesIncidenciasCrudRepository.findById((id));
    }

    public E_PrioridadIncidencia crearPrioridadesIncidencias(E_PrioridadIncidencia prioridadesIncidencias) {
        return prioridadesIncidenciasCrudRepository.save(prioridadesIncidencias);
    }

    public E_PrioridadIncidencia actualizarPrioridadesIncidencias(E_PrioridadIncidencia prioridadesIncidencias) {
        return prioridadesIncidenciasCrudRepository.save(prioridadesIncidencias);
    }

    public void eliminarPrioridadesIncidencias(Long id) {
        prioridadesIncidenciasCrudRepository.deleteById(id);
    }
}
