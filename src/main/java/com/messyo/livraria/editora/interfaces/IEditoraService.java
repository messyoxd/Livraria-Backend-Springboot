package com.messyo.livraria.editora.interfaces;

import com.messyo.livraria.editora.dto.EditoraDTO;

import java.util.List;

public interface IEditoraService {
    EditoraDTO create(EditoraDTO editoraDTO);
    void verifyIfExistsByName(String id);
    EditoraDTO findById(Long id);
    List<EditoraDTO> getAll();
    Long removeById(Long id);
    EditoraDTO updateEditora(EditoraDTO editoraDTO);
}
