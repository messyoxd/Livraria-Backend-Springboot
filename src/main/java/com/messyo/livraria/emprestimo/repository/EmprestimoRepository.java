package com.messyo.livraria.emprestimo.repository;

import com.messyo.livraria.emprestimo.entity.Emprestimo;
import com.messyo.livraria.livro.entity.Livro;
import com.messyo.livraria.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    List<Emprestimo> findByUsuarioEmprestimo(Usuario usuario);
    List<Emprestimo> findByLivroEmprestimo(Livro livro);
    List<Emprestimo> findByLivroEmprestimoAndUsuarioEmprestimo(Usuario usuario, Livro livro);


}
