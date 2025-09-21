package com.restandpray.rest_and_pray_api.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank String fullName,
        @NotBlank String username,
        @NotBlank String password
) {

}