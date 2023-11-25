package com.example.demo.Servicio;

import com.example.demo.Entidad.E_CambioEstado;
import com.example.demo.Repositorio.R_CambioEstado;
import com.example.demo.Repositorio.R_EstadosIncidencias;
import com.example.demo.Repositorio.R_Incidencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class S_CambioEstado {

    private R_CambioEstado cambioEstadoRepository;
    private R_Incidencia incidenciaCrudRepository;
    private R_EstadosIncidencias estadosIncidenciasCrudRepository;

    @Autowired

    public S_CambioEstado(R_CambioEstado cambioEstadoCrudRepository, R_Incidencia incidenciaCrudRepository, R_EstadosIncidencias estadosIncidenciasCrudRepository) {
        this.cambioEstadoRepository = cambioEstadoCrudRepository;
        this.incidenciaCrudRepository = incidenciaCrudRepository;
        this.estadosIncidenciasCrudRepository = estadosIncidenciasCrudRepository;
    }

    public List<E_CambioEstado> obtenerTodosLosCambioEstado() {
        return (List<E_CambioEstado>) cambioEstadoRepository.findAll();
    }


    public Optional<E_CambioEstado> obtenerCambioEstadoPorId(Long id) {
        return cambioEstadoRepository.findById(id);}




    public E_CambioEstado crearCambioEstado(E_CambioEstado cambioEstado) {
        cambioEstado.setIncidencia(incidenciaCrudRepository.findById(cambioEstado.getIncidencia().getIncidenciaID()).get());
        cambioEstado.setEstado(estadosIncidenciasCrudRepository.findById(cambioEstado.getEstado().getEstadoID()).get());
        cambioEstado.setFechaRegistro(LocalDate.now());
        return cambioEstadoRepository.save(cambioEstado);
    }

    public void eliminarCambioEstado(Long id) {
        cambioEstadoRepository.deleteById(id);
    }
}
