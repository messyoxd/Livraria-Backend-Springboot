package com.messyo.livraria.livro.exception;

import javax.persistence.EntityNotFoundException;

public class LivroNotFoundException extends EntityNotFoundException {
    public LivroNotFoundException(Long id) {
        super(String.format("Livro with id %d not found!", id));
    }

    public LivroNotFoundException(String message) {
        super(message);
    }
}
