package com.lazywork.estructuraCERS.servicios;

import com.lazywork.estructuraCERS.entidad.Incidencia;
import com.lazywork.estructuraCERS.repositorio.EstadoIncidenciaRepository;
import com.lazywork.estructuraCERS.repositorio.IncidenciaRepository;
import com.lazywork.estructuraCERS.repositorio.PrioridadIncidenciaRepository;
import com.lazywork.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class
IncidenciaService {

    private IncidenciaRepository incidenciaRepository;

    private EstadoIncidenciaRepository estadosIncidenciasCrudRepository;
     private PrioridadIncidenciaRepository prioridadesIncidenciasCrudRepository;
     private UserRepository usuarioCrudRepository;
    @Autowired
    public IncidenciaService(IncidenciaRepository incidenciaRepository, EstadoIncidenciaRepository estadosIncidenciasCrudRepository, PrioridadIncidenciaRepository prioridadesIncidenciasCrudRepository, UserRepository usuarioCrudRepository) {
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
