package com.restandpray.rest_and_pray_api.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank(message = "Username cannot be blank")
        @Size(min = 1, max = 50, message = "Username must be between 1 and 50 characters")
        @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username can only contain letters, numbers and underscore")
                String username,

        @NotBlank(message = "Password cannot be blank")
        @Size(min = 6, max = 100, message = "Password is too long")
        String password

) {}