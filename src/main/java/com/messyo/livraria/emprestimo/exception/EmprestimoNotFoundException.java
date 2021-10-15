package com.messyo.livraria.emprestimo.exception;

import javax.persistence.EntityNotFoundException;

public class EmprestimoNotFoundException extends EntityNotFoundException {
    public EmprestimoNotFoundException(Long id) {
        super(String.format("Emprestimo with id %d not found!", id));
    }
}
