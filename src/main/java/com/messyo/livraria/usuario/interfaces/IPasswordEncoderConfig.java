package com.messyo.livraria.usuario.interfaces;

import org.springframework.security.crypto.password.PasswordEncoder;

public interface IPasswordEncoderConfig {
    PasswordEncoder passwordEncoder();
}
