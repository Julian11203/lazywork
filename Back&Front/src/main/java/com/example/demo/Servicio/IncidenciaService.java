package com.example.demo.Servicio;

import com.example.demo.Entidad.Incidencia;
import com.example.demo.Repositorio.EstadosIncidenciasCrudRepository;
import com.example.demo.Repositorio.IncidenciaCrudRepository;
import com.example.demo.Repositorio.PrioridadesIncidenciasCrudRepository;
import com.example.demo.Repositorio.UsuarioCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class
IncidenciaService {

    @PersistenceContext
    private EntityManager entityManager;
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
        incidencia.setUsuario(usuarioCrudRepository.findById(incidencia.getUsuario().getUsuarioid()).get());
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

    public List<Incidencia> obtenerIncidenciasEstado4() {
        // Utiliza el método de tu repositorio para obtener las incidencias con estado 4
        return incidenciaRepository.findByEstado_EstadoID(4L);
    }
    public List<Incidencia> obtenerIncidenciasEstado3() {
        // Utiliza el método de tu repositorio para obtener las incidencias con estado 4
        return incidenciaRepository.findByEstado_EstadoID(3L);
    }

    public List<Incidencia> obtenerIncidenciasEstado1() {
        // Utiliza el método de tu repositorio para obtener las incidencias con estado 4
        return incidenciaRepository.findByEstado_EstadoID(1L);
    }
}
