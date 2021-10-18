package com.messyo.livraria.emprestimo.interfaces;

import com.messyo.livraria.emprestimo.dto.EmprestimoDTO;

import java.util.List;

public interface IEmprestimoService {
    EmprestimoDTO create(EmprestimoDTO emprestimoDTO);
    void verifyIfDoesNotExists(Long id);
    EmprestimoDTO findById(Long id);
    List<EmprestimoDTO> findByUsuarioId(long usuarioId);
    List<EmprestimoDTO> findByLivroId(long livroId);
    List<EmprestimoDTO> findByUsuarioIdAndLivroId(long usuarioId, long livroId);
    List<EmprestimoDTO> getAll();
    EmprestimoDTO updateEmprestimo(EmprestimoDTO emprestimoDTO);
    Long devolucaoDeLivro(EmprestimoDTO emprestimo);
    Long removeById(Long id);
}
