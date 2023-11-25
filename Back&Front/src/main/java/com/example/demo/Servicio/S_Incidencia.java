package com.example.demo.Servicio;

import com.example.demo.Entidad.E_Incidencia;
import com.example.demo.Repositorio.R_EstadosIncidencias;
import com.example.demo.Repositorio.R_Incidencia;
import com.example.demo.Repositorio.R_PrioridadesIncidencias;
import com.example.demo.Repositorio.R_UsuarioBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class
S_Incidencia {

    @PersistenceContext
    private EntityManager entityManager;
    private R_Incidencia incidenciaRepository;

    private R_EstadosIncidencias estadosIncidenciasCrudRepository;
    private R_PrioridadesIncidencias prioridadesIncidenciasCrudRepository;
    private R_UsuarioBack usuarioCrudRepository;
    @Autowired
    public S_Incidencia(R_Incidencia incidenciaRepository, R_EstadosIncidencias estadosIncidenciasCrudRepository, R_PrioridadesIncidencias prioridadesIncidenciasCrudRepository, R_UsuarioBack usuarioCrudRepository) {
        this.incidenciaRepository = incidenciaRepository;
        this.estadosIncidenciasCrudRepository = estadosIncidenciasCrudRepository;
        this.prioridadesIncidenciasCrudRepository = prioridadesIncidenciasCrudRepository;
        this.usuarioCrudRepository = usuarioCrudRepository;
    }

    public List<E_Incidencia> obtenerTodasLasIncidencias() {
        return (List<E_Incidencia>) incidenciaRepository.findAll();
    }

    public Optional<E_Incidencia> obtenerIncidenciaPorId(Long id) {
        return incidenciaRepository.findById((id));
    }
    public E_Incidencia crearIncidencia(E_Incidencia incidencia) {

        incidencia.setEstado(estadosIncidenciasCrudRepository.findById(incidencia.getEstado().getEstadoID()).get());
        incidencia.setPrioridad(prioridadesIncidenciasCrudRepository.findById(incidencia.getPrioridad().getPrioridadID()).get());
        incidencia.setUsuarioback(usuarioCrudRepository.findById(incidencia.getUsuarioback().getId()).get());
        incidencia.setFechaRegistro(LocalDate.now());

        return incidenciaRepository.save(incidencia);
    }

    public E_Incidencia actualizarIncidencia(E_Incidencia incidencia) {

        incidencia.setFechaRegistro(LocalDate.now());
        return incidenciaRepository.save(incidencia);
    }
    public void eliminarIncidencia(Long id) {
        incidenciaRepository.deleteById(id);
    }


    @Transactional
    public void actualizarIncidenciasNivel2Prioridad2() {
        String sql = "\n" +
                "UPDATE incidencias\n" +
                "SET usuarioid = (\n" +
                "    SELECT usuarioid\n" +
                "    FROM usuariosback\n" +
                "    WHERE nivel_soporte = 'nivel 2'\n" +
                "    LIMIT 1\n" +
                ")\n" +
                "WHERE prioridadid = (\n" +
                "    SELECT prioridadid\n" +
                "    FROM prioridades_incidencia\n" +
                "    WHERE tipo_prioridad = 'media'\n" +
                "    LIMIT 1\n" +
                ");";

        entityManager.createNativeQuery(sql).executeUpdate();
    }

    @Transactional
    public void actualizarIncidenciasNivel1Prioridad1() {
        String sql = "\n" +
                "UPDATE incidencias\n" +
                "SET usuarioid = (\n" +
                "    SELECT usuarioid\n" +
                "    FROM usuariosback\n" +
                "    WHERE nivel_soporte = 'nivel 1'\n" +
                "    LIMIT 1\n" +
                ")\n" +
                "WHERE prioridadid = (\n" +
                "    SELECT prioridadid\n" +
                "    FROM prioridades_incidencia\n" +
                "    WHERE tipo_prioridad = 'baja'\n" +
                "    LIMIT 1\n" +
                ");";

        entityManager.createNativeQuery(sql).executeUpdate();
    }

    @Transactional
    public void actualizarIncidenciasNivel3Prioridad3() {
        String sql = "UPDATE incidencias " +
                "SET usuarioid = ( " +
                "    SELECT usuarioid " +
                "    FROM usuariosback " +
                "    WHERE nivel_soporte = 'nivel 3' " +
                ") " +
                "WHERE prioridadid = ( " +
                "    SELECT prioridadid " +
                "    FROM prioridades_incidencia " +
                "    WHERE tipo_prioridad = 'alta' " +
                ")";

        entityManager.createNativeQuery(sql).executeUpdate();
    }
    @Transactional
    public void actualizarIncidenciasNivel4Prioridad4() {
        String sql = "UPDATE incidencias " +
                "SET usuarioid = ( " +
                "    SELECT usuarioid " +
                "    FROM usuariosback " +
                "    WHERE nivel_soporte = 'nivel 4' " +
                ") " +
                "WHERE prioridadid = ( " +
                "    SELECT prioridadid " +
                "    FROM prioridades_incidencia " +
                "    WHERE tipo_prioridad = 'critica' " +
                ")";

        entityManager.createNativeQuery(sql).executeUpdate();
    }

    public List<E_Incidencia> obtenerIncidenciasEstado4() {
        // Utiliza el método de tu repositorio para obtener las incidencias con estado 4
        return incidenciaRepository.findByEstado_EstadoID(4L);
    }
    public List<E_Incidencia> obtenerIncidenciasEstado3() {
        // Utiliza el método de tu repositorio para obtener las incidencias con estado 4
        return incidenciaRepository.findByEstado_EstadoID(3L);
    }

    public List<E_Incidencia> obtenerIncidenciasEstado1() {
        // Utiliza el método de tu repositorio para obtener las incidencias con estado 4
        return incidenciaRepository.findByEstado_EstadoID(1L);
    }

}
