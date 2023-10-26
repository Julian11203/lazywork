package com.lazywork.auth;

import com.lazywork.JWT.JwtService;
import com.lazywork.User.Role;
import com.lazywork.User.User;
import com.lazywork.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    public AuthResponse login(LoginRequest request) {
        return null;
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .documento(request.getDocumento())
                .nivelSoporte(request.getNivelSoporte())
                .contraseña(request.getContraseña())
                .rol(Role.USER)
                .build();
        userRepository.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
