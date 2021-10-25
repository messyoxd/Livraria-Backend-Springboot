package com.messyo.livraria.livro.viewmodel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.messyo.livraria.editora.dto.EditoraDTO;
import com.messyo.livraria.editora.viewmodel.EditoraViewModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivroViewModel {

    @NotNull
    private Long livroId;

    private EditoraViewModel editora;

    private String autor;

    private String nomeLivro;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate lancamento;

    private Integer quantidadeDisponivel;

    private String createdAt;

    private String updatedAt;
}
