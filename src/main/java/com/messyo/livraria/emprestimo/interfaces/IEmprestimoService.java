package com.messyo.livraria.emprestimo.interfaces;

import com.messyo.livraria.emprestimo.dto.EmprestimoDTO;
import com.messyo.livraria.emprestimo.viewmodel.EmprestimoViewmodel;

import java.util.List;

public interface IEmprestimoService {
    EmprestimoDTO create(EmprestimoDTO emprestimoDTO);

    //    private void verifyIfExists(Long id) {
    //        _emprestimoRepository.findById(id)
    //                .ifPresent(emprestimo -> {
    //                    throw new EmprestimoAlreadyExistsException(emprestimo.getEmprestimoId());
    //                });
    //    }
    void verifyIfUsuarioExists(Long id);

    void verifyIfLivroExists(Long id);

    void verifyIfDoesNotExists(Long id);
    EmprestimoDTO findById(Long id);
    List<EmprestimoDTO> findByUsuarioId(long usuarioId);
    List<EmprestimoDTO> findByLivroId(long livroId);
    List<EmprestimoDTO> findByUsuarioIdAndLivroId(long usuarioId, long livroId);
    List<EmprestimoDTO> getAll();
    EmprestimoDTO updateEmprestimo(EmprestimoViewmodel vm);
    Long devolucaoDeLivro(Long emprestimoId);
    Long removeById(Long id);
}
