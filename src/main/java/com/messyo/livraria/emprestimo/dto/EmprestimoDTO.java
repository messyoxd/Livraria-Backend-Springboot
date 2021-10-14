package com.messyo.livraria.emprestimo.dto;

import com.messyo.livraria.livro.dto.LivroDTO;
import com.messyo.livraria.usuario.dto.UsuarioDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class EmprestimoDTO {
    private Long emprestimoId;

    @NotNull
    private UsuarioDTO usuarioEmprestimo;

    @NotNull
    private LivroDTO livroEmprestimo;

    @NotNull(message = "O campo Previsão de Devolução é obrigatório")
    private LocalDateTime previsaoDevolucao;

    private LocalDateTime dataDevolucao;

    @NotBlank(message = "O campo Status do Empréstimo é obrigatório")
    @Size(max = 20)
    private String statusEmprestimo;
}
