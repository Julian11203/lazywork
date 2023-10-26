package com.lazywork.auth;

import com.lazywork.User.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String username;
    String nombre;
    String apellido;
    String nivelSoporte;
    String documento;
    String contraseña;
    Role rol;

}
