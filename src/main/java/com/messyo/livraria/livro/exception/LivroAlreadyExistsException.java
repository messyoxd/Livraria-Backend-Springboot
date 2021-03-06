package com.messyo.livraria.livro.exception;

import javax.persistence.EntityExistsException;

public class LivroAlreadyExistsException extends EntityExistsException {
    public LivroAlreadyExistsException(Long id) {
        super(String.format("Livro with id %d already exists!", id));
    }
    public LivroAlreadyExistsException(String nome) {
        super(String.format("Livro with nome %s already exists!", nome));
    }
}
