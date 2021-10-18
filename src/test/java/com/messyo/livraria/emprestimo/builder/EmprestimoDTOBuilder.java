package com.messyo.livraria.emprestimo.builder;

import com.messyo.livraria.emprestimo.dto.EmprestimoDTO;
import com.messyo.livraria.livro.builder.LivroDTOBuilder;
import com.messyo.livraria.livro.dto.LivroDTO;
import com.messyo.livraria.usuario.builder.UsuarioDTOBuilder;
import com.messyo.livraria.usuario.dto.UsuarioDTO;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
public class EmprestimoDTOBuilder {
    private final Long emprestimoId = 1L;

    private final UsuarioDTO usuarioEmprestimo = UsuarioDTOBuilder.builder().build().buildUsuarioDTO();

    private final LivroDTO livroEmprestimo = LivroDTOBuilder.builder().build().buildLivroDTO();

    private final Date previsaoDevolucao = new Date();

    private final Date dataDevolucao = new Date(new Date().getTime() + (2 * 1000 * 60 * 60 * 24)); // 2 days

    private final String statusEmprestimo = "Emprestado";

    public EmprestimoDTO buildEmprestimoDTO() {
        return new EmprestimoDTO(emprestimoId, usuarioEmprestimo, livroEmprestimo, previsaoDevolucao, dataDevolucao, statusEmprestimo);
    }
}
