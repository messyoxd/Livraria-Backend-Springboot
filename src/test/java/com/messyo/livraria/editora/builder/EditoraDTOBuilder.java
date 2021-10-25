package com.messyo.livraria.editora.builder;

import com.messyo.livraria.editora.dto.EditoraDTO;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class EditoraDTOBuilder {

    @Builder.Default
    private final Long editoraId = 1L;

    @Builder.Default
    private final String nome = "Editora Memes";

    @Builder.Default
    private final String cidade = "Fortaleza";

    private final String createdAt = LocalDateTime.now().toString();

    private final String updatedAt = LocalDateTime.now().toString();

    public EditoraDTO buildEditoraDTO(){
        return new EditoraDTO(editoraId, nome, cidade, createdAt, updatedAt);
    }
}
