package com.messyo.livraria.emprestimo.exception;

import javax.persistence.EntityNotFoundException;

public class EmprestimoLivroNotAvailableException extends EntityNotFoundException {
    public EmprestimoLivroNotAvailableException(Long id) {
        super(String.format("Livro with id %d not available!", id));
    }
}
