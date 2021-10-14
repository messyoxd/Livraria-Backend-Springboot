package com.messyo.livraria.editora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditoraDTO {

    private Long editoraId;

    @NotBlank(message = "O campo Nome é obrigatório")
    @Size(max = 255)
    private String nome;

    @NotBlank(message = "O campo Cidade é obrigatório")
    private String cidade;
}
