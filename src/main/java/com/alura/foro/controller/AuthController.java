package com.alura.foro.controller;

import com.alura.foro.dto.UserDTO;
import com.alura.foro.model.User;
import com.alura.foro.service.AuthService;
import com.alura.foro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        System.out.println("Username recibido: " + userDTO.getUsername());
        System.out.println("Password recibido: " + userDTO.getPassword());
        String token = authService.authenticate(userDTO.getUsername(), userDTO.getPassword());
        return ResponseEntity.ok(token);
    }
}