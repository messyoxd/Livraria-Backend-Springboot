package com.messyo.livraria.editora.controller;

import com.messyo.livraria.editora.dto.EditoraDTO;
import com.messyo.livraria.editora.interfaces.IEditoraService;
import com.messyo.livraria.editora.viewmodel.EditoraViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/editoras")
public class EditoraController implements EditoraControllerDocs {

    @Autowired
    private IEditoraService _editoraService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EditoraDTO create(@RequestBody @Valid EditoraDTO editoraDTO) {
        return _editoraService.create(editoraDTO);
    }

    @GetMapping("/{id}")
    public EditoraDTO findById(@PathVariable Long id) {
        return _editoraService.findById(id);
    }

    @GetMapping()
    public List<EditoraDTO> getAll() {
        return _editoraService.getAll();
    }

    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Long removeById(@PathVariable Long id) {
        return _editoraService.removeById(id);
    }

    @PutMapping
    public EditoraDTO update(@RequestBody @Valid EditoraViewModel vm) {
        return _editoraService.updateEditora(vm);
    }
}
