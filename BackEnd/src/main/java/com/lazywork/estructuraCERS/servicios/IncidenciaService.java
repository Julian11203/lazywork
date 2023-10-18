package com.lazywork.estructuraCERS.servicios;

import com.lazywork.estructuraCERS.entidad.Incidencia;
import com.lazywork.estructuraCERS.repositorio.EstadosIncidenciasCrudRepository;
import com.lazywork.estructuraCERS.repositorio.IncidenciaCrudRepository;
import com.lazywork.estructuraCERS.repositorio.PrioridadesIncidenciasCrudRepository;
import com.lazywork.estructuraCERS.repositorio.UsuarioCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class
IncidenciaService {

    private IncidenciaCrudRepository incidenciaRepository;

    private EstadosIncidenciasCrudRepository estadosIncidenciasCrudRepository;
     private PrioridadesIncidenciasCrudRepository prioridadesIncidenciasCrudRepository;
     private UsuarioCrudRepository usuarioCrudRepository;
    @Autowired
    public IncidenciaService(IncidenciaCrudRepository incidenciaRepository, EstadosIncidenciasCrudRepository estadosIncidenciasCrudRepository, PrioridadesIncidenciasCrudRepository prioridadesIncidenciasCrudRepository, UsuarioCrudRepository usuarioCrudRepository) {
        this.incidenciaRepository = incidenciaRepository;
        this.estadosIncidenciasCrudRepository = estadosIncidenciasCrudRepository;
        this.prioridadesIncidenciasCrudRepository = prioridadesIncidenciasCrudRepository;
        this.usuarioCrudRepository = usuarioCrudRepository;
    }

    public List<Incidencia> obtenerTodasLasIncidencias() {
        return (List<Incidencia>) incidenciaRepository.findAll();
    }

    public Optional<Incidencia> obtenerIncidenciaPorId(Long id) {
        return incidenciaRepository.findById((id));
    }

    public Incidencia crearIncidencia(Incidencia incidencia) {

        incidencia.setEstado(estadosIncidenciasCrudRepository.findById(incidencia.getEstado().getEstadoID()).get());
        incidencia.setPrioridad(prioridadesIncidenciasCrudRepository.findById(incidencia.getPrioridad().getPrioridadID()).get());
        incidencia.setUsuario(usuarioCrudRepository.findById(incidencia.getUsuario().getId()).get());
        incidencia.setFechaRegistro(LocalDate.now());

        return incidenciaRepository.save(incidencia);
    }

    public Incidencia actualizarIncidencia(Incidencia incidencia) {

        incidencia.setFechaRegistro(LocalDate.now());
        return incidenciaRepository.save(incidencia);
    }

    public void eliminarIncidencia(Long id) {
        incidenciaRepository.deleteById(id);
    }
}
