package com.messyo.livraria.emprestimo.viewmodel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.messyo.livraria.livro.viewmodel.LivroViewModel;
import com.messyo.livraria.usuario.viewmodel.UsuarioViewModel;
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
public class EmprestimoViewmodel {
    @NotNull
    private Long emprestimoId;

    private UsuarioViewModel usuarioEmprestimo;

    private LivroViewModel livroEmprestimo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate previsaoDevolucao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataDevolucao;

    private String statusEmprestimo;

    private String createdAt;

    private String updatedAt;
}
