package com.lazywork.servicios;

import com.lazywork.entidad.Tiene;
import com.lazywork.repositorio.TieneCrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TieneServicio {
    TieneCrudRepository tieneCrudRepository;

    public TieneServicio(TieneCrudRepository tieneCrudRepository) {
        this.tieneCrudRepository = tieneCrudRepository;
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
