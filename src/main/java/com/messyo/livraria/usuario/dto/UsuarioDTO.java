package com.messyo.livraria.usuario.dto;

import com.messyo.livraria.usuario.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long usuarioId;

    @NotBlank(message = "O campo Nome Completo é obrigatório")
    @Size(max = 50)
    private String nomeCompleto;

    @NotBlank(message = "O campo Enderoço é obrigatório")
    @Size(max = 100)
    private String endereco;

    @NotBlank(message = "O campo Cidade é obrigatório")
    @Size(max = 100)
    private String cidade;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    private String createdAt;

    private String updatedAt;
}
