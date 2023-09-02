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

    public InicioServicio(InicioCrudRepository inicioCrudRepository) {
        this.inicioCrudRepository = inicioCrudRepository;
    }

    public Inicio actualizarInicio(Inicio inicio) {
        return inicioCrudRepository.save(inicio);
    }

    public void eliminarInicio(String idInicio){
        inicioCrudRepository.deleteById(idInicio);
    }

    public boolean existeInicio(String id){
        return inicioCrudRepository.existsById(id);
    }

    public Inicio obtenerInicioPorId(String id){
        return inicioCrudRepository.findById(id).get();
    }

    public List<Inicio> listarInicios(){
        return (List<Inicio>) inicioCrudRepository.findAll();
    }

    public Inicio insertarInicio(Inicio inicio) {
        // Aquí no es necesario cargar el usuario ya que JPA administrará la relación
        return inicioCrudRepository.save(inicio);
    }
}
