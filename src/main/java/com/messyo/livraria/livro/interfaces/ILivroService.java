package com.messyo.livraria.livro.interfaces;

import com.messyo.livraria.livro.dto.LivroDTO;

import java.util.List;

public interface ILivroService {
    LivroDTO create(LivroDTO livroDTO);
    void verifyIfExistsByName(Long id);
    void verifyIfLivroIsAvailable(Long id);
    LivroDTO findById(Long id);
    List<LivroDTO> getAll();
    LivroDTO updateLivro(LivroDTO livroDTO);
    Long removeById(Long id);
    void setLivroQuantidadeById(Long livroId, Integer quantidade);
    void decrementarQuantidade(Long livroId, Integer quantidade);
    void incrementarQuantidade(Long livroId, Integer quantidade);
}
