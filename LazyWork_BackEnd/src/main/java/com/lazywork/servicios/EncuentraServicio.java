package com.lazywork.servicios;

import com.lazywork.entidad.Encuentra;
import com.lazywork.repositorio.EncuentraCrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncuentraServicio {
    EncuentraCrudRepository encuentraCrudRepository;

    public EncuentraServicio(EncuentraCrudRepository encuentraCrudRepository) {
        this.encuentraCrudRepository = encuentraCrudRepository;
    }
    public boolean existeEncuentra(String id){
        return encuentraCrudRepository.existsById(id);
    }
    public List<Encuentra> listarEncuentra(){
        return (List<Encuentra>) encuentraCrudRepository.findAll();
    }
    public Encuentra encuentraPorId(String id){
        return encuentraCrudRepository.findById(id).get();
    }
}
