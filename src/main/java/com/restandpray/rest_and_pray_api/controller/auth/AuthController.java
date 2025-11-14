package com.restandpray.rest_and_pray_api.controller.auth;

import com.restandpray.rest_and_pray_api.dto.auth.AuthResponse;
import com.restandpray.rest_and_pray_api.dto.auth.RegisterRequest;
import com.restandpray.rest_and_pray_api.service.auth.AuthService;
import com.restandpray.rest_and_pray_api.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import com.restandpray.rest_and_pray_api.dto.auth.LoginRequest;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;
    private final UserService userService;
    private final AuthenticationManager authManager;

    public AuthController(AuthService service, UserService userService, AuthenticationManager authManager) {
        this.authService = service;
        this.userService = userService;
        this.authManager = authManager;
    }

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest r) {
        log.info("Register request for username: {}", r.username());

        var u = authService.register(r);

        log.info("Registration successful: {}", u.getUsername());
        return new AuthResponse(u.getUsername(), u.getFullName(), u.getRole().name());
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest r, HttpServletRequest request) {
        log.info("Login attempt: {}", r.username());

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(r.username(), r.password())
        );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);

        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

        var u = userService.getByUsernameOrThrow(r.username());

        log.info("Login success for: {}", r.username());

        return new AuthResponse(u.getUsername(), u.getFullName(), u.getRole().name());
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth != null ? auth.getName() : "unknown";

        log.info("Logout request by {}", username);

        var session = request.getSession(false);
        if (session != null) session.invalidate();
        SecurityContextHolder.clearContext();

        log.info("Logout completed for {}", username);
    }
}