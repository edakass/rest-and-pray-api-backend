package com.restandpray.rest_and_pray_api.service.auth;

import com.restandpray.rest_and_pray_api.dto.auth.RegisterRequest;
import com.restandpray.rest_and_pray_api.entity.user.User;
import com.restandpray.rest_and_pray_api.repository.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.server.ResponseStatusException;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class AuthService {

    private final UserRepository users;
    private final PasswordEncoder encoder;

    public AuthService(UserRepository users, PasswordEncoder encoder) {
        this.users = users;
        this.encoder = encoder;
    }

    @Transactional
    public User register(RegisterRequest r) {
        if (users.existsByUsername(r.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already taken");
        }
        var u = new User();
        u.setUsername(r.username());
        u.setFullName(r.fullName());
        u.setPassword(encoder.encode(r.password()));
        u.setRole(User.Role.USER);
        return users.save(u);
    }
}