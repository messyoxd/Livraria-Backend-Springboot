package com.messyo.livraria.livro.controller;

import com.messyo.livraria.editora.builder.EditoraDTOBuilder;
import com.messyo.livraria.editora.controller.EditoraController;
import com.messyo.livraria.editora.service.EditoraService;
import com.messyo.livraria.livro.builder.LivroDTOBuilder;
import com.messyo.livraria.livro.service.LivroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@ExtendWith(MockitoExtension.class)
public class LivroControllerTest {
    @Mock
    private LivroService livroService;

    @InjectMocks
    private LivroController livroController;

    private MockMvc mockMvc;

    private LivroDTOBuilder livroDTOBuilder;

    @BeforeEach
    void setUp() {
        livroDTOBuilder = LivroDTOBuilder.builder().build();
        mockMvc = MockMvcBuilders.standaloneSetup(livroDTOBuilder)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }
}
