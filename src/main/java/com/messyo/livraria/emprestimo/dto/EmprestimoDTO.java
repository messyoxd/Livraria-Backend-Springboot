package com.messyo.livraria.emprestimo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.messyo.livraria.livro.dto.LivroDTO;
import com.messyo.livraria.usuario.dto.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmprestimoDTO {
    private Long emprestimoId;

    @NotNull
    private UsuarioDTO usuarioEmprestimo;

    @NotNull
    private LivroDTO livroEmprestimo;

    @NotNull(message = "O campo Previsão de Devolução é obrigatório")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate previsaoDevolucao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataDevolucao;

    @NotBlank(message = "O campo Status do Empréstimo é obrigatório")
    @Size(max = 30)
    private String statusEmprestimo;
}
