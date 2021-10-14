package com.messyo.livraria.livro.repository;

import com.messyo.livraria.livro.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByAutor(String autor);

    @Query(
            "SELECT l FROM Livro l WHERE l.lancamento = ?1"
    )
    List<Livro> findByLancamento(String parameter);
}
