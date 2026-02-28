package com.aritra.sharenotes.dto;

import java.util.Set;

public record AuthResponse(
        String token,
        String username,
        String email,
        Set<String> roles
) {}
