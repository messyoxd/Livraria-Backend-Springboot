package com.messyo.livraria.livro.interfaces;

import com.messyo.livraria.livro.dto.LivroDTO;
import com.messyo.livraria.livro.viewmodel.LivroViewModel;

import java.util.List;

public interface ILivroService {
    LivroDTO create(LivroDTO livroDTO);

    void verifyIfEditoraExists(Long editoraId);

    void verifyIfExistsByName(String nome);
    Integer verifyIfLivroIsAvailable(Long id);
    LivroDTO findById(Long id);
    List<LivroDTO> getAll();
    LivroDTO updateLivro(LivroViewModel vm);
    Long removeById(Long id);
    void setLivroQuantidadeById(Long livroId, Integer quantidade);
    void decrementarQuantidade(Long livroId, Integer quantidade);
    void incrementarQuantidade(Long livroId, Integer quantidade);
}
