package com.example.demo.Controlador;

import com.example.demo.Servicio.UserService;
import com.example.demo.persistence.entity.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable String correo){
        if(userService.existById(correo)){
            return ResponseEntity.ok().body(userService.findUserById(correo).get());
        }else{
            return ResponseEntity.ok(null);
        }
    }
}
