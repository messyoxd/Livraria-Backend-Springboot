package com.messyo.livraria.usuario.controller;

import com.messyo.livraria.livro.builder.LivroDTOBuilder;
import com.messyo.livraria.livro.controller.LivroController;
import com.messyo.livraria.livro.service.LivroService;
import com.messyo.livraria.usuario.builder.UsuarioDTOBuilder;
import com.messyo.livraria.usuario.service.UsuarioService;
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
public class UsuarioControllerTest {
    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    private MockMvc mockMvc;

    private UsuarioDTOBuilder usuarioDTOBuilder;

    @BeforeEach
    void setUp() {
        usuarioDTOBuilder = UsuarioDTOBuilder.builder().build();
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioDTOBuilder)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }
}
