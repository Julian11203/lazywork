package com.example.demo.Repositorio;

import com.example.demo.Entidad.E_Usuarioback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface R_UsuarioBack extends JpaRepository<E_Usuarioback, Long> {

    E_Usuarioback findByCorreo(String email);

    E_Usuarioback findByNivelSoporte(String nivelSoporte);
}
