package com.messyo.livraria.editora.exception;

import javax.persistence.EntityExistsException;

public class EditoraAlreadyExistsException extends EntityExistsException {
    public EditoraAlreadyExistsException(Long id) {
        super(String.format("Editora with id %d already exists!", id));
    }
}
