//package com.messyo.livraria.emprestimo.builder;
//
//import com.messyo.livraria.emprestimo.dto.EmprestimoDTO;
//import com.messyo.livraria.livro.builder.LivroDTOBuilder;
//import com.messyo.livraria.livro.dto.LivroDTO;
//import com.messyo.livraria.usuario.builder.UsuarioDTOBuilder;
//import com.messyo.livraria.usuario.dto.UsuarioDTO;
//import lombok.Builder;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//@Builder
//public class EmprestimoDTOBuilder {
//    private final Long emprestimoId = 1L;
//
//    private final UsuarioDTO usuarioEmprestimo = UsuarioDTOBuilder.builder().build().buildUsuarioDTO();
//
//    private final LivroDTO livroEmprestimo = LivroDTOBuilder.builder().build().buildLivroDTO();
//
//    private final LocalDate previsaoDevolucao = LocalDate.now();
//
//    private final LocalDate dataDevolucao = LocalDate.now().plusDays(2);
//
//    private final String statusEmprestimo = "Emprestado";
//
//    private final String createdAt = LocalDateTime.now().toString();
//
//    private final String updatedAt = LocalDateTime.now().toString();
//
//    public EmprestimoDTO buildEmprestimoDTO() {
//        return new EmprestimoDTO(emprestimoId, usuarioEmprestimo, livroEmprestimo, previsaoDevolucao, dataDevolucao, statusEmprestimo, createdAt, updatedAt);
//    }
//}
