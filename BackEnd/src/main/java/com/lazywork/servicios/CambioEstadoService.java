package com.lazywork.servicios;


import com.lazywork.entidad.CambioEstado;
import com.lazywork.repositorio.CambioEstadoCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CambioEstadoService {

    @Autowired
    private CambioEstadoCrudRepository cambioEstadoRepository;

    public List<CambioEstado> obtenerTodosLosCambioEstado() {
        return (List<CambioEstado>) cambioEstadoRepository.findAll();
    }

    public Optional<CambioEstado> obtenerCambioEstadoPorId(String id) {
        return cambioEstadoRepository.findById(id);
    }

    public CambioEstado crearCambioEstado(CambioEstado cambioEstado) {
        return cambioEstadoRepository.save(cambioEstado);
    }

    public void eliminarCambioEstado(String id) {
        cambioEstadoRepository.deleteById(id);
    }
}

