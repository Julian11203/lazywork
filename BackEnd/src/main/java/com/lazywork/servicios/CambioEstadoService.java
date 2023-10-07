package com.lazywork.servicios;

import com.lazywork.entidad.CambioEstado;
import com.lazywork.repositorio.CambioEstadoCrudRepository;
import com.lazywork.repositorio.EstadosIncidenciasCrudRepository;
import com.lazywork.repositorio.IncidenciaCrudRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CambioEstadoService {

    private  CambioEstadoCrudRepository cambioEstadoRepository;
    private  IncidenciaCrudRepository incidenciaCrudRepository;
    private  EstadosIncidenciasCrudRepository estadosIncidenciasCrudRepository;

    @Autowired

    public CambioEstadoService(CambioEstadoCrudRepository cambioEstadoCrudRepository, IncidenciaCrudRepository incidenciaCrudRepository, EstadosIncidenciasCrudRepository estadosIncidenciasCrudRepository) {
        this.cambioEstadoRepository = cambioEstadoCrudRepository;
        this.incidenciaCrudRepository = incidenciaCrudRepository;
        this.estadosIncidenciasCrudRepository = estadosIncidenciasCrudRepository;
    }

    public List<CambioEstado> obtenerTodosLosCambioEstado() {
        return (List<CambioEstado>) cambioEstadoRepository.findAll();
    }


    public Optional<CambioEstado> obtenerCambioEstadoPorId(Long id) {
        return cambioEstadoRepository.findById(id);}




    public CambioEstado crearCambioEstado(CambioEstado cambioEstado) {
        cambioEstado.setIncidencia(incidenciaCrudRepository.findById(cambioEstado.getIncidencia().getIncidenciaID()).get());
        cambioEstado.setEstado(estadosIncidenciasCrudRepository.findById(cambioEstado.getEstado().getEstadoID()).get());
        cambioEstado.setFechaRegistro(LocalDate.now());
        return cambioEstadoRepository.save(cambioEstado);
    }

    public void eliminarCambioEstado(Long id) {
        cambioEstadoRepository.deleteById(id);
    }
}
