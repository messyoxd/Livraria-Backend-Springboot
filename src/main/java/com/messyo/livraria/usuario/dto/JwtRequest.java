package com.messyo.livraria.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
