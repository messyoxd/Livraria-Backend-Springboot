package com.messyo.livraria.emprestimo.controller;

import com.messyo.livraria.emprestimo.dto.EmprestimoDTO;
import com.messyo.livraria.emprestimo.interfaces.IEmprestimoService;
import com.messyo.livraria.emprestimo.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/emprestimos")
public class EmprestimoController implements EmprestimoControllerDocs {
    @Autowired
    private IEmprestimoService _emprestimoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EmprestimoDTO create(@RequestBody @Valid EmprestimoDTO emprestimoDTO) {
        return _emprestimoService.create(emprestimoDTO);
    }

    @GetMapping("/")
    public List<EmprestimoDTO> getAll() {
        return _emprestimoService.getAll();
    }

    @GetMapping("/{id}")
    public EmprestimoDTO findById(@PathVariable Long id) {
        return _emprestimoService.findById(id);
    }

    @GetMapping("/usuario/{id}")
    public List<EmprestimoDTO> findByUsuarioId(@PathVariable Long id) {
        return _emprestimoService.findByUsuarioId(id);
    }

    @GetMapping("/livro/{id}")
    public List<EmprestimoDTO> findByLivroId(@PathVariable Long id) {
        return _emprestimoService.findByLivroId(id);
    }

    @GetMapping("/usuario/{usuarioId}/livro/{livroId}")
    public List<EmprestimoDTO> findByUsuarioIdAndLivroId(@PathVariable Long usuarioId, @PathVariable Long livroId) {
        return _emprestimoService.findByUsuarioIdAndLivroId(usuarioId, livroId);
    }

    @DeleteMapping("/{id}")
    public Long removeById(@PathVariable Long id) {
        return _emprestimoService.removeById(id);
    }

    @PutMapping
    public EmprestimoDTO update(@RequestBody @Valid EmprestimoDTO emprestimoDTO) {
        return _emprestimoService.updateEmprestimo(emprestimoDTO);
    }

    @PostMapping("/devolucao")
    @ResponseStatus(HttpStatus.OK)
    public Long devolucao(@RequestBody @Valid EmprestimoDTO emprestimoDTO) {
        return _emprestimoService.devolucaoDeLivro(emprestimoDTO);
    }
}
