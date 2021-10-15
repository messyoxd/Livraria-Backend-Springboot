package com.messyo.livraria.editora.exception;

import javax.persistence.EntityNotFoundException;

public class EditoraNotFoundException extends EntityNotFoundException {
    public EditoraNotFoundException(Long id) {
        super(String.format("Editora with id %d not found!", id));
    }
}
