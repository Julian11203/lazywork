package com.lazywork.estructuraCERS.servicios;

import com.lazywork.estructuraCERS.entidad.EstadoIncidencia;
import com.lazywork.estructuraCERS.repositorio.EstadosIncidenciasCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadosIncidenciasService {

    @Autowired
    private EstadosIncidenciasCrudRepository estadosIncidenciasRepository;

    public List<EstadoIncidencia> obtenerTodosLosEstadosIncidencias() {
        return (List<EstadoIncidencia>) estadosIncidenciasRepository.findAll();
    }

    public Optional<EstadoIncidencia> obtenerEstadosIncidenciasPorId(Long id) {
        return estadosIncidenciasRepository.findById((id));
    }

    public EstadoIncidencia crearEstadosIncidencias(EstadoIncidencia estadosIncidencias) {
        return estadosIncidenciasRepository.save(estadosIncidencias);
    }

    public EstadoIncidencia actualizarEstadosIncidencias(EstadoIncidencia estadosIncidencias) {
        return estadosIncidenciasRepository.save(estadosIncidencias);
    }

    public void eliminarEstadosIncidencias(Long id) {
        estadosIncidenciasRepository.deleteById(id);
    }
}
