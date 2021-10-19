package com.messyo.livraria.usuario.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtResponse {
    private final String jwtToken;
}
