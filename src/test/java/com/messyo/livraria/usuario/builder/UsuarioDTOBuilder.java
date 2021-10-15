package com.messyo.livraria.usuario.builder;

import com.messyo.livraria.livro.dto.LivroDTO;
import com.messyo.livraria.usuario.dto.UsuarioDTO;
import lombok.Builder;

@Builder
public class UsuarioDTOBuilder {
    private final Long usuarioId = 1L;

    private final String nomeCompleto = "Messyo";

    private final String endereco = "Rua 123";

    private final String cidade = "Cidade de Townsvile";

    public UsuarioDTO buildUsuarioDTO() {
        return new UsuarioDTO(usuarioId, nomeCompleto, endereco, cidade);
    }
}
