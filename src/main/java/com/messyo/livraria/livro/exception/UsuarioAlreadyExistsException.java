package com.messyo.livraria.livro.exception;

import javax.persistence.EntityExistsException;

public class UsuarioAlreadyExistsException extends EntityExistsException {
    public UsuarioAlreadyExistsException(Long id) {
        super(String.format("Usuario with id %d already exists!", id));
    }

    public UsuarioAlreadyExistsException(String email) {
        super(String.format("Usuario with email %s already exists!", email));
    }
}
