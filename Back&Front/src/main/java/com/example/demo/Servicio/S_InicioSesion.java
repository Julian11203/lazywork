package com.example.demo.Servicio;

import com.example.demo.Entidad.E_InicioSesion;
import com.example.demo.Repositorio.R_InicioSesion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class S_InicioSesion {
    private final R_InicioSesion inicioSesionRepository;

    @Autowired
    public S_InicioSesion(R_InicioSesion inicioSesionRepository) {
        this.inicioSesionRepository = inicioSesionRepository;
    }

    public List<E_InicioSesion> listaIniciosSesion() {
        return (List<E_InicioSesion>) inicioSesionRepository.findAll();
    }

    public E_InicioSesion insertarInicioSesion(E_InicioSesion inicioSesion) {
        return inicioSesionRepository.save(inicioSesion);
    }


    public E_InicioSesion actualizarInicioSesion(Long id, E_InicioSesion inicioSesionActualizado) {
        if (existeInicioSesion(id)) {
            E_InicioSesion inicioSesionExistente = inicioSesionRepository.findById(String.valueOf(id)).orElse(null);
            if (inicioSesionExistente != null) {
                // Actualizar los campos necesarios del inicio de sesión existente
                inicioSesionExistente.setUsuarioback(inicioSesionActualizado.getUsuarioback());
                inicioSesionExistente.setFechaHoraInicio(inicioSesionActualizado.getFechaHoraInicio());
                // Continuar actualizando otros campos según tus necesidades

                // Guardar el inicio de sesión actualizado en la base de datos
                return inicioSesionRepository.save(inicioSesionExistente);
            }
        }
        return null;
    }
    public Optional<E_InicioSesion> findInicioSesionById(Long id) {
        return inicioSesionRepository.findById(String.valueOf(id));
    }
    public void eliminarInicioSesion(Long id) {
        inicioSesionRepository.deleteById(String.valueOf(id));
    }

    public boolean existeInicioSesion(Long id) {
        return inicioSesionRepository.existsById(String.valueOf(id));
    }
}