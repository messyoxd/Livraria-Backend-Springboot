package com.messyo.livraria.editora.builder;

import com.messyo.livraria.editora.dto.EditoraDTO;
import lombok.Builder;

@Builder
public class EditoraDTOBuilder {

    @Builder.Default
    private final Long editoraId = 1L;

    @Builder.Default
    private final String nome = "Editora Memes";

    @Builder.Default
    private final String cidade = "Fortaleza";

    public EditoraDTO buildEditoraDTO(){
        return new EditoraDTO(editoraId, nome, cidade);
    }
}
