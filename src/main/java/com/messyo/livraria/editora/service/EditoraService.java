package com.messyo.livraria.editora.service;

import com.messyo.livraria.editora.dto.EditoraDTO;
import com.messyo.livraria.editora.entity.Editora;
import com.messyo.livraria.editora.exception.EditoraAlreadyExistsException;
import com.messyo.livraria.editora.exception.EditoraNotFoundException;
import com.messyo.livraria.editora.mapper.EditoraMapper;
import com.messyo.livraria.editora.repository.EditoraRepository;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EditoraService {
    private EditoraRepository _editoraRepository;

    private final EditoraMapper _editoraMapper = EditoraMapper.INSTANCE;

    @Autowired
    public EditoraService(EditoraRepository editoraRepository) {
        _editoraRepository = editoraRepository;
    }

    public EditoraDTO create(EditoraDTO editoraDTO) {
        verifyIfExists(editoraDTO.getEditoraId());
        Editora editoraToSave = _editoraMapper.toModel(editoraDTO);
        Editora savedEditora = _editoraRepository.save(editoraToSave);
        return _editoraMapper.toDTO(savedEditora);
    }

    private void verifyIfExists(Long id) {
        _editoraRepository.findById(id)
                .ifPresent(editora -> {
                    throw new EditoraAlreadyExistsException((editora.getEditoraId()));
                });
    }

    public EditoraDTO findById(Long id) throws EditoraNotFoundException {
        Editora e = _editoraRepository.findById(id).orElseThrow(() -> new EditoraNotFoundException(id));

        return _editoraMapper.toDTO(e);
    }

    public List<EditoraDTO> getAll() {
        return _editoraRepository.findAll().stream().map(_editoraMapper::toDTO).collect(Collectors.toList());
    }

    public Long removeById(Long id) throws EditoraNotFoundException {
        EditoraDTO e = this.findById(id);
        Editora editoraToRemove = _editoraMapper.toModel(e);
        _editoraRepository.delete(editoraToRemove);
        return e.getEditoraId();
    }

    public EditoraDTO updateEditora(EditoraDTO editoraDTO) throws EditoraNotFoundException {
        EditoraDTO e = this.findById(editoraDTO.getEditoraId());
        e.setCidade(StringUtils.isEmpty(editoraDTO.getCidade()) ? e.getCidade() : editoraDTO.getCidade());
        e.setNome(StringUtils.isEmpty(editoraDTO.getNome()) ? e.getNome() : editoraDTO.getNome());
        Editora editoraToUpdate = _editoraMapper.toModel(e);
        _editoraRepository.save(editoraToUpdate);
        return e;
    }
}
