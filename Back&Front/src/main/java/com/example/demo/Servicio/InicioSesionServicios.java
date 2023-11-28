package com.example.demo.Servicio;

import com.example.demo.Entidad.InicioSesion;
import com.example.demo.Repositorio.InicioSesionCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InicioSesionServicios {
    private final InicioSesionCrudRepository inicioSesionRepository;

    @Autowired
    public InicioSesionServicios(InicioSesionCrudRepository inicioSesionRepository) {
        this.inicioSesionRepository = inicioSesionRepository;
    }

    public List<InicioSesion> listaIniciosSesion() {
        return (List<InicioSesion>) inicioSesionRepository.findAll();
    }

    public Optional<InicioSesion> findInicioSesionById(Long id) {
        return inicioSesionRepository.findById(String.valueOf(id));
    }

    public InicioSesion insertarInicioSesion(InicioSesion inicioSesion) {
        return inicioSesionRepository.save(inicioSesion);
    }

    public InicioSesion actualizarInicioSesion(Long id, InicioSesion inicioSesionActualizado) {
        if (existeInicioSesion(id)) {
            InicioSesion inicioSesionExistente = inicioSesionRepository.findById(String.valueOf(id)).orElse(null);
            if (inicioSesionExistente != null) {
                // Actualizar los campos necesarios del inicio de sesión existente
                inicioSesionExistente.setUsuarioback(inicioSesionActualizado.getUsuarioback());
                inicioSesionExistente.setFechaHoraInicio(inicioSesionActualizado.getFechaHoraInicio());
                inicioSesionExistente.setFechaHoraFin(inicioSesionActualizado.getFechaHoraFin());
                inicioSesionExistente.setTiempodesesion(inicioSesionActualizado.getTiempodesesion());
                inicioSesionExistente.setNombreuser(inicioSesionActualizado.getNombreuser());

                // Guardar el inicio de sesión actualizado en la base de datos
                return inicioSesionRepository.save(inicioSesionExistente);
            }
        }
        return null;
    }

    public void eliminarInicioSesion(Long id) {
        inicioSesionRepository.deleteById(String.valueOf(id));
    }

    public boolean existeInicioSesion(Long id) {
        return inicioSesionRepository.existsById(String.valueOf(id));
    }
}