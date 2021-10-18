package com.messyo.livraria.livro.controller;

import com.messyo.livraria.livro.dto.LivroDTO;
import com.messyo.livraria.livro.exception.LivroNotFoundException;
import com.messyo.livraria.livro.interfaces.ILivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/livros")
public class LivroController implements LivroControllerDocs {

    @Autowired
    private ILivroService _livroService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LivroDTO create(@RequestBody @Valid LivroDTO livroDTO) {
        return _livroService.create(livroDTO);
    }

    @GetMapping("/")
    public List<LivroDTO> getAll() {
        return _livroService.getAll();
    }

    /*
    @GetMapping("/find-by/{parameter}/{value}")
    public List<LivroDTO> findLivroByParameter(@PathVariable String parameter, @PathVariable String value) throws LivroNotFoundException {
        return _livroService.findLivroByParameter(parameter, value);
    }

     */

    @GetMapping("/{id}")
    public LivroDTO findById(@PathVariable Long id) throws LivroNotFoundException {
        return _livroService.findById(id);
    }

    @DeleteMapping("/{id}")
    public Long removeById(@PathVariable Long id) {
        return _livroService.removeById(id);
    }

    @PutMapping
    public LivroDTO update(@RequestBody @Valid LivroDTO livroDTO) {
        return _livroService.updateLivro(livroDTO);
    }
}
