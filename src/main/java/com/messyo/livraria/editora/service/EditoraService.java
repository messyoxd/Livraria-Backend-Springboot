package com.messyo.livraria.editora.service;

import com.messyo.livraria.editora.dto.EditoraDTO;
import com.messyo.livraria.editora.entity.Editora;
import com.messyo.livraria.editora.mapper.EditoraMapper;
import com.messyo.livraria.editora.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditoraService {
    private EditoraRepository _editoraRepository;

    private final EditoraMapper _editoraMapper = EditoraMapper.INSTANCE;

    @Autowired
    public EditoraService(EditoraRepository editoraRepository) {
        _editoraRepository = editoraRepository;
    }

    public String create(EditoraDTO editoraDTO) {
        Editora editoraToSave = _editoraMapper.toModel(editoraDTO);
        Editora savedEditora = null;
        try {
            savedEditora = _editoraRepository.save(editoraToSave);
        } catch (Exception e) {
            System.out.println("*************************************");
            System.out.println(e);
            System.out.println("*************************************");
            return "Erro no sistema!";
        }
        return "Editora criada com id " + savedEditora.getEditoraId();
    }

//    public Editora verifyAndGetIfExists(Long id) {
//        return _editoraRepository.findById(id)
//                .orElseThrow(() -> new EditoraNotFoundException(id));
//    }

//    public EditoraDTO findById(Long id) throws EntityNotFoundException {
//        Editora e = _editoraRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
//
//        return _editoraMapper.toDTO(e);
//    }
//
//    public Long removeById(Long id) throws EditoraNotFoundException {
//        EditoraDTO e = this.findById(id);
//        Editora editoraToRemove = _editoraMapper.toModel(e);
//        _editoraRepository.delete(editoraToRemove);
//        return e.getEditoraId();
//    }
//
//    public EditoraDTO updateEditora(EditoraViewModel editoraVM) throws EditoraNotFoundException {
//        EditoraDTO e = this.findById(editoraVM.getEditoraId());
//        e.setCidade(StringUtils.isEmpty(editoraVM.getCidade()) ? e.getCidade() : editoraVM.getCidade());
//        e.setNome(StringUtils.isEmpty(editoraVM.getNome()) ? e.getNome() : editoraVM.getNome());
//        e.setUpdatedAt(new Date());
//        Editora editoraToUpdate = _editoraMapper.toModel(e);
//        _editoraRepository.save(editoraToUpdate);
//        return e;
//    }
}
