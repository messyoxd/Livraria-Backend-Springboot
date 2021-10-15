package com.messyo.livraria.emprestimo.exception;

import javax.persistence.EntityExistsException;

public class EmprestimoAlreadyExistsException extends EntityExistsException {
    public EmprestimoAlreadyExistsException(Long id) {
        super(String.format("Emprestimo with id %d already exists!", id));
    }
}
