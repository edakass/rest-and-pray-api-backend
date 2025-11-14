package com.restandpray.rest_and_pray_api.service.user;

import com.restandpray.rest_and_pray_api.entity.user.User;
import com.restandpray.rest_and_pray_api.repository.user.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getByUsernameOrThrow(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
