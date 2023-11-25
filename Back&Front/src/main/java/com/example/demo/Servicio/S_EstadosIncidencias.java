package com.example.demo.Servicio;

import com.example.demo.Entidad.E_EstadoIncidencia;
import com.example.demo.Repositorio.R_EstadosIncidencias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class S_EstadosIncidencias {

    @Autowired
    private R_EstadosIncidencias estadosIncidenciasRepository;

    public List<E_EstadoIncidencia> obtenerTodosLosEstadosIncidencias() {
        return (List<E_EstadoIncidencia>) estadosIncidenciasRepository.findAll();
    }

    public Optional<E_EstadoIncidencia> obtenerEstadosIncidenciasPorId(Long id) {
        return estadosIncidenciasRepository.findById((id));
    }

    public E_EstadoIncidencia crearEstadosIncidencias(E_EstadoIncidencia estadosIncidencias) {
        return estadosIncidenciasRepository.save(estadosIncidencias);
    }

    public E_EstadoIncidencia actualizarEstadosIncidencias(E_EstadoIncidencia estadosIncidencias) {
        return estadosIncidenciasRepository.save(estadosIncidencias);
    }

    public void eliminarEstadosIncidencias(Long id) {
        estadosIncidenciasRepository.deleteById(id);
    }
}
