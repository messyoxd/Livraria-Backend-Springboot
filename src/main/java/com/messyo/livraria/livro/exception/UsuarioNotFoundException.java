package com.messyo.livraria.livro.exception;

import javax.persistence.EntityNotFoundException;

public class UsuarioNotFoundException extends EntityNotFoundException {
    public UsuarioNotFoundException(Long id) {
        super(String.format("Usuario with id %d not found!", id));
    }
}
