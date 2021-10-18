package com.messyo.livraria.editora.service;

import com.messyo.livraria.editora.dto.EditoraDTO;
import com.messyo.livraria.editora.entity.Editora;
import com.messyo.livraria.editora.exception.EditoraAlreadyExistsException;
import com.messyo.livraria.editora.exception.EditoraNotFoundException;
import com.messyo.livraria.editora.interfaces.IEditoraService;
import com.messyo.livraria.editora.mapper.EditoraMapper;
import com.messyo.livraria.editora.repository.EditoraRepository;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EditoraService implements IEditoraService {
    private EditoraRepository _editoraRepository;

    @Autowired
    protected EditoraMapper _editoraMapper;

    @Autowired
    public EditoraService(EditoraRepository editoraRepository) {
        _editoraRepository = editoraRepository;
    }

    @Override
    public EditoraDTO create(EditoraDTO editoraDTO) {
        verifyIfExistsByName(editoraDTO.getNome());
        Editora editoraToSave = _editoraMapper.toModel(editoraDTO);
        Editora savedEditora = _editoraRepository.save(editoraToSave);
        return _editoraMapper.toDTO(savedEditora);
    }

    @Override
    public void verifyIfExistsByName(String nome) {
        _editoraRepository.findByNome(nome)
                .ifPresent(editora -> {
                    throw new EditoraAlreadyExistsException((editora.getNome()));
                });
    }

    @Override
    public EditoraDTO findById(Long id) throws EditoraNotFoundException {
        Editora e = _editoraRepository.findById(id).orElseThrow(() -> new EditoraNotFoundException(id));

        return _editoraMapper.toDTO(e);
    }

    @Override
    public List<EditoraDTO> getAll() {
        return _editoraRepository.findAll().stream().map(_editoraMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Long removeById(Long id) {
        EditoraDTO e = this.findById(id);
        Editora editoraToRemove = _editoraMapper.toModel(e);
        _editoraRepository.delete(editoraToRemove);
        return e.getEditoraId();
    }

    @Override
    public EditoraDTO updateEditora(EditoraDTO editoraDTO) {
        EditoraDTO e = this.findById(editoraDTO.getEditoraId());
        e.setCidade(StringUtils.isEmpty(editoraDTO.getCidade()) ? e.getCidade() : editoraDTO.getCidade());
        e.setNome(StringUtils.isEmpty(editoraDTO.getNome()) ? e.getNome() : editoraDTO.getNome());
        Editora editoraToUpdate = _editoraMapper.toModel(e);
        _editoraRepository.save(editoraToUpdate);
        return e;
    }

}
