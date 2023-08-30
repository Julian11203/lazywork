package com.lazywork.servicios;

import com.lazywork.entidad.Tiene;
import com.lazywork.repositorio.IncidenciaCrudRepository;
import com.lazywork.repositorio.PrioridadCrudRepository;
import com.lazywork.repositorio.TieneCrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TieneServicio {

    private TieneCrudRepository tieneCrudRepository;
    private IncidenciaCrudRepository incidenciaCrudRepository;
    private PrioridadCrudRepository prioridadCrudRepository;

    public TieneServicio(TieneCrudRepository tieneCrudRepository,IncidenciaCrudRepository incidenciaCrudRepository ,PrioridadCrudRepository prioridadCrudRepository) {
        this.tieneCrudRepository = tieneCrudRepository;
        this.incidenciaCrudRepository= incidenciaCrudRepository;
        this.prioridadCrudRepository = prioridadCrudRepository;
    }
    public Tiene insertarTiene (Tiene tiene){
    tiene.setIncidencia(incidenciaCrudRepository.findById(tiene.getIncidencia().getNoIncidencia()).get());
    tiene.setPrioridad(prioridadCrudRepository.findById(tiene.getPrioridad().getIdPrioridad()).get());
        return tieneCrudRepository.save(tiene);
    }
    public void eliminarTiene(String idTiene){
        tieneCrudRepository.deleteById(idTiene);
    }
    public boolean existeTiene(String id){
        return tieneCrudRepository.existsById(id);
    }
    public List<Tiene> listarTiene(){
        return (List<Tiene>) tieneCrudRepository.findAll();
    }
    public Tiene tienePorId(String id){
        return tieneCrudRepository.findById(id).get();
    }

}
