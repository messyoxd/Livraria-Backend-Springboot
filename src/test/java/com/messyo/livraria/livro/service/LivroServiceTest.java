package com.messyo.livraria.livro.service;

import com.messyo.livraria.editora.builder.EditoraDTOBuilder;
import com.messyo.livraria.editora.mapper.EditoraMapper;
import com.messyo.livraria.editora.repository.EditoraRepository;
import com.messyo.livraria.editora.service.EditoraService;
import com.messyo.livraria.livro.builder.LivroDTOBuilder;
import com.messyo.livraria.livro.mapper.LivroMapper;
import com.messyo.livraria.livro.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LivroServiceTest {
    private final LivroMapper livroMapper = LivroMapper.INSTANCE;

    @Mock
    private LivroRepository livroRepository;

    @InjectMocks
    private LivroService livroService;

    private LivroDTOBuilder livroDTOBuilder;

    @BeforeEach
    void setUp() {
        livroDTOBuilder = LivroDTOBuilder.builder().build();
    }
}
