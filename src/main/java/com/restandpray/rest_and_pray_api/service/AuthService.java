package com.restandpray.rest_and_pray_api.service;

import com.restandpray.rest_and_pray_api.dto.RegisterRequest;
import com.restandpray.rest_and_pray_api.entity.User;
import com.restandpray.rest_and_pray_api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository users;
    private final PasswordEncoder encoder;

    public AuthService(UserRepository users, PasswordEncoder encoder) {
        this.users = users;
        this.encoder = encoder;
    }

    public User register(RegisterRequest r) {
        if (users.existsByUsername(r.username()))
            throw new IllegalArgumentException("Username already used");
        User u = new User();
        u.setUsername(r.username());
        u.setPassword(encoder.encode(r.password()));
        u.setFullName(r.fullName());
        u.setRole(User.Role.USER);
        return users.save(u);
    }
}
