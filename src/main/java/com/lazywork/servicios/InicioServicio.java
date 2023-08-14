package com.lazywork.servicios;

import com.lazywork.entidad.Inicio;
import com.lazywork.repositorio.InicioCrudRepository;
import com.lazywork.repositorio.UsuarioCrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InicioServicio {

    InicioCrudRepository inicioCrudRepository;

    public InicioServicio(InicioCrudRepository inicioCrudRepository) {
        this.inicioCrudRepository = inicioCrudRepository;
    }

    public boolean existeInicio(String id){
        return  inicioCrudRepository.existsById(id);
    }
    public Inicio inicioPorId(String id){
        return  inicioCrudRepository.findById(id).get();
    }
    public List<Inicio> listaInicios (){
        return (List<Inicio>) inicioCrudRepository.findAll();
    }

}
