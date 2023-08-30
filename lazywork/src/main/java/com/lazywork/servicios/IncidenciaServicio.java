package com.lazywork.servicios;

import com.lazywork.entidad.Incidencia;
import com.lazywork.entidad.Usuario;
import com.lazywork.repositorio.IncidenciaCrudRepository;
import com.lazywork.repositorio.UsuarioCrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidenciaServicio {

    private IncidenciaCrudRepository incidenciaCrudRepository;
    private UsuarioCrudRepository usuarioCrudRepository;


    public IncidenciaServicio(IncidenciaCrudRepository incidenciaCrudRepository,UsuarioCrudRepository usuarioCrudRepository ) {
        this.incidenciaCrudRepository = incidenciaCrudRepository;
        this.usuarioCrudRepository = usuarioCrudRepository;
    }
    public Incidencia insertarIncidencia (Incidencia incidencia){
        incidencia.setUsuario(usuarioCrudRepository.findById(incidencia.getUsuario().getIdUser()).get());
        return incidenciaCrudRepository.save(incidencia);
    }
    public void eliminarIncidencia(String idIncidencia){
        incidenciaCrudRepository.deleteById(idIncidencia);
    }
    public boolean existeIncidencia(String id){
        return incidenciaCrudRepository.existsById(id);
    }
    public Incidencia incidenciaPorId(String id){
        return incidenciaCrudRepository.findById(id).get();
    }
    public List<Incidencia> listaIncidencias(){
        return (List<Incidencia>) incidenciaCrudRepository.findAll();
    }
}
