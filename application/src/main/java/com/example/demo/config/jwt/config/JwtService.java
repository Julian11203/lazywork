package com.example.demo.config.jwt.config;

import com.example.demo.config.user.config.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    public String generateToken(UserDetails user){
        return null;
    }
}
