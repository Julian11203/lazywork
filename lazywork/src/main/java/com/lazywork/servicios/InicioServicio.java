package com.lazywork.servicios;

import com.lazywork.entidad.Inicio;
import com.lazywork.repositorio.InicioCrudRepository;
import com.lazywork.repositorio.UsuarioCrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InicioServicio {

    private InicioCrudRepository inicioCrudRepository;
    private UsuarioCrudRepository usuarioCrudRepository;

    public InicioServicio(InicioCrudRepository inicioCrudRepository, UsuarioCrudRepository usuarioCrudRepository) {
        this.inicioCrudRepository = inicioCrudRepository;
        this.usuarioCrudRepository = usuarioCrudRepository;
    }

    public Inicio insertarInicio(Inicio inicio){
        inicio.setUsuario(usuarioCrudRepository.findById(inicio.getUsuario().getIdUser()).get());
        return inicioCrudRepository.save(inicio);
    }
    public void eliminarInicio(String idInicio){
        inicioCrudRepository.deleteById(idInicio);
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