package com.messyo.livraria.usuario.viewmodel;

import com.messyo.livraria.usuario.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioViewModel {

    @NotNull
    private Long usuarioId;

    private String nomeCompleto;

    private String endereco;

    private String cidade;

    private String email;

    private Role role;

    private String createdAt;

    private String updatedAt;
}
