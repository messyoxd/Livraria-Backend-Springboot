package com.messyo.livraria.usuario.service;

import com.messyo.livraria.livro.builder.LivroDTOBuilder;
import com.messyo.livraria.livro.mapper.LivroMapper;
import com.messyo.livraria.livro.repository.LivroRepository;
import com.messyo.livraria.livro.service.LivroService;
import com.messyo.livraria.usuario.builder.UsuarioDTOBuilder;
import com.messyo.livraria.usuario.mapper.UsuarioMapper;
import com.messyo.livraria.usuario.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
    private final UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private UsuarioDTOBuilder usuarioDTOBuilder;

    @BeforeEach
    void setUp() {
        usuarioDTOBuilder = UsuarioDTOBuilder.builder().build();
    }
}
