package com.example.demo.Repositorio;

import com.example.demo.persistence.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}