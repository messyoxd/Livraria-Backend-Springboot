package com.messyo.livraria.editora.repository;

import com.messyo.livraria.editora.entity.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {
    Optional<Editora> findByNome(String nome);
}
