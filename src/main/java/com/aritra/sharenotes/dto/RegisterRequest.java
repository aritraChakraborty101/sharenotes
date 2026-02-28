package com.aritra.sharenotes.dto;

public record RegisterRequest(
        String username,
        String email,
        String password
) {}
