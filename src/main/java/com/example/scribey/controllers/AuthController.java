package com.example.scribey.controllers;

import com.example.scribey.domain.user.*;
import com.example.scribey.infra.security.TokenService;
import com.example.scribey.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO data, HttpServletResponse response) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(((User) auth.getPrincipal()).getName(), ((User) auth.getPrincipal()).getEmail(), token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data, HttpServletResponse response) {
        if (userService.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();
        userService.save(data);
        return login(new LoginRequestDTO(data.email(), data.password()), response);
    }
}
