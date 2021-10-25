package com.messyo.livraria.editora.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditoraViewModel {

    @NotNull
    private Long editoraId;

    private String nome;

    private String cidade;

    private String createdAt;

    private String updatedAt;
}
