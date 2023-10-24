package com.lazywork.estructuraCERS.servicios;

import com.lazywork.estructuraCERS.entidad.CambioEstado;
import com.lazywork.estructuraCERS.repositorio.CambioEstadoRepository;
import com.lazywork.estructuraCERS.repositorio.EstadoIncidenciaRepository;
import com.lazywork.estructuraCERS.repositorio.IncidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CambioEstadoService {

    private CambioEstadoRepository cambioEstadoRepository;
    private IncidenciaRepository incidenciaCrudRepository;
    private EstadoIncidenciaRepository estadosIncidenciasCrudRepository;

    @Autowired

    public CambioEstadoService(CambioEstadoRepository cambioEstadoCrudRepository, IncidenciaRepository incidenciaCrudRepository, EstadoIncidenciaRepository estadosIncidenciasCrudRepository) {
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
