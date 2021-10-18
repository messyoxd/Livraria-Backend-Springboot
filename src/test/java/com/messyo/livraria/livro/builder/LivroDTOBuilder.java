package com.messyo.livraria.livro.builder;

import com.messyo.livraria.editora.builder.EditoraDTOBuilder;
import com.messyo.livraria.editora.dto.EditoraDTO;
import com.messyo.livraria.livro.dto.LivroDTO;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
public class LivroDTOBuilder {
    private final Long livroId = 1L;

    private final EditoraDTO editora = EditoraDTOBuilder.builder().build().buildEditoraDTO();

    private final String autor = "Zezinho";

    private final String nomeLivro = "Livrinho";

    private final Date lancamento = new Date();

    private final Integer quantidadeDisponivel = 5;

    public LivroDTO buildLivroDTO() {
        return new LivroDTO(livroId, editora, autor, nomeLivro, lancamento, quantidadeDisponivel);
    }
}
