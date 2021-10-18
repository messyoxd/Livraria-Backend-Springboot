package com.messyo.livraria.livro.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.messyo.livraria.editora.dto.EditoraDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivroDTO {

    private Long livroId;

    @NotNull
    private EditoraDTO editora;

    @NotBlank(message = "O campo Autor é obrigatório")
    @Size(max = 75)
    private String autor;

    @NotBlank(message = "O campo Nome Livro é obrigatório")
    @Size(max = 75)
    private String nomeLivro;

    @NotNull(message = "O campo Lançamento é obrigatório")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY")
    private LocalDate lancamento;

    @NotNull(message = "O campo Quantidade Disponível é obrigatório")
    private Integer quantidadeDisponivel;
}
