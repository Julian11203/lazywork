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
                .nombre(request.getFirstname())
                .apellido(request.getLastname())
                .contraseña(request.getPassword())
                .rol(Role.USER)
                .build();
        userRepository.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
