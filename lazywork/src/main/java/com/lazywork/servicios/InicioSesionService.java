package com.lazywork.servicios;

import com.lazywork.entidad.InicioSesion;
import com.lazywork.repositorio.InicioSesionCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InicioSesionService {

    @Autowired
    private InicioSesionCrudRepository inicioSesionRepository;

    public List<InicioSesion> obtenerTodosLosIniciosSesion() {
        return (List<InicioSesion>) inicioSesionRepository.findAll();
    }

    public Optional<InicioSesion> obtenerInicioSesionPorId(String id) {
        return inicioSesionRepository.findById(id);
    }

    public InicioSesion crearInicioSesion(InicioSesion inicioSesion) {
        return inicioSesionRepository.save(inicioSesion);
    }

    public void eliminarInicioSesion(String id) {
        inicioSesionRepository.deleteById(id);
    }
    public InicioSesion actualizarInicioSesion(String id, InicioSesion inicioSesionActualizado) {
        Optional<InicioSesion> inicioSesionExistente = inicioSesionRepository.findById(id);
        if (inicioSesionExistente.isPresent()) {
            InicioSesion inicioSesion = inicioSesionExistente.get();
            // Actualiza los campos que desees con los valores de inicioSesionActualizado
            inicioSesion.setUsuario(inicioSesionActualizado.getUsuario());

            // Actualiza otros campos según sea necesario

            // Guarda el inicio de sesión actualizado en la base de datos
            return inicioSesionRepository.save(inicioSesion);
        } else {
            // Manejo de error si el inicio de sesión no existe
            throw new RuntimeException("Inicio de sesión no encontrado: " + id);
        }
    }

}
