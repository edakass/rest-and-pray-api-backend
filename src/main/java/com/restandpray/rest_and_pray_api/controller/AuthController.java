package com.restandpray.rest_and_pray_api.controller;

import com.restandpray.rest_and_pray_api.dto.AuthResponse;
import com.restandpray.rest_and_pray_api.dto.RegisterRequest;
import com.restandpray.rest_and_pray_api.repository.UserRepository;
import com.restandpray.rest_and_pray_api.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;
    private final UserRepository users;

    public AuthController(AuthService service, UserRepository users) {
        this.service = service;
        this.users = users;
    }

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest r) {
        var u = service.register(r);
        return new AuthResponse(u.getUsername(), u.getFullName(), u.getRole().name());
    }

    @GetMapping("/me")
    public AuthResponse me(Authentication auth) {
        var u = users.findByUsername(auth.getName()).orElseThrow();
        return new AuthResponse(u.getUsername(), u.getFullName(), u.getRole().name());
    }
}