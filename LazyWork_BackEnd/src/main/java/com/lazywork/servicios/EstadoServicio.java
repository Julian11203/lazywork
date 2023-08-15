package com.lazywork.servicios;

import com.lazywork.entidad.Estado;
import com.lazywork.repositorio.EstadoCrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EstadoServicio {
    private final EstadoCrudRepository estadoCrudRepository;

    public EstadoServicio(EstadoCrudRepository estadoCrudRepository) {
        this.estadoCrudRepository = estadoCrudRepository;
    }

    public List<Estado> listarEstados() {
        return (List<Estado>) estadoCrudRepository.findAll();
    }


    public Estado obtenerEstadoPorIdEstado(String idEstado) {
        return estadoCrudRepository.findById(idEstado).orElse(null);
    }

    public Estado crearEstado(Estado estado) {
        return estadoCrudRepository.save(estado);
    }


    public Estado actualizarEstado(Estado estado) {
        return estadoCrudRepository.save(estado);
    }

    public void eliminarEstado(String id){
        estadoCrudRepository.deleteById(id);
    }
}
