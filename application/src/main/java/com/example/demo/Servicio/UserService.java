package com.example.demo.Servicio;

import com.example.demo.Repositorio.UserRepository;
import com.example.demo.persistence.entity.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findUserById(String correo) {
        return userRepository.findById(correo);
    }

    public boolean existById(String correo) {
        return userRepository.existsById(correo);
    }
}
