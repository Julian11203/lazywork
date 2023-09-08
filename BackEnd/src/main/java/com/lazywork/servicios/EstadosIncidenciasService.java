package com.lazywork.servicios;

import com.lazywork.entidad.EstadoIncidencia;
import com.lazywork.repositorio.EstadosIncidenciasCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadosIncidenciasService {

    @Autowired
    private EstadosIncidenciasCrudRepository estadosIncidenciasCrudRepository;



    public List<EstadoIncidencia> obtenerTodasLosEstadosIncidencias() {
        return (List<EstadoIncidencia>) estadosIncidenciasCrudRepository.findAll();
    }

    public Optional<EstadoIncidencia> obtenerEstadosIncidenciasPorId(Long id) {
        return estadosIncidenciasCrudRepository.findById(String.valueOf(id));
    }

    public EstadoIncidencia crearEstadosIncidencias(EstadoIncidencia estadosIncidencias) {
        return estadosIncidenciasCrudRepository.save(estadosIncidencias);
    }

    public EstadoIncidencia actualizarEstadosIncidencias(EstadoIncidencia estadosIncidencias) {
        return estadosIncidenciasCrudRepository.save(estadosIncidencias);
    }

    public void eliminarEstadosIncidencias(Long id) {
        estadosIncidenciasCrudRepository.deleteById(String.valueOf(id));
    }
}
