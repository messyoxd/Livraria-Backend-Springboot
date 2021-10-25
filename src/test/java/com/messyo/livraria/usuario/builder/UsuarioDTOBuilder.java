package com.messyo.livraria.usuario.builder;

import com.messyo.livraria.usuario.dto.UsuarioDTO;
import com.messyo.livraria.usuario.enums.Role;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class UsuarioDTOBuilder {
    private final Long usuarioId = 1L;

    private final String nomeCompleto = "Messyo";

    private final String endereco = "Rua 123";

    private final String cidade = "Cidade de Townsvile";

    private final String email = "teste@email.com";

    private final String password = "qwe123";

    private final Role role = Role.USER;

    private final String createdAt = LocalDateTime.now().toString();

    private final String updatedAt = LocalDateTime.now().toString();

    public UsuarioDTO buildUsuarioDTO() {
        return new UsuarioDTO(usuarioId, nomeCompleto, endereco, cidade, email, password, role, createdAt, updatedAt);
    }
}
