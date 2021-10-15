package com.messyo.livraria.editora.controller;

import com.messyo.livraria.editora.builder.EditoraDTOBuilder;
import com.messyo.livraria.editora.service.EditoraService;
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
public class EditoraControllerTest {

    @Mock
    private EditoraService editoraService;

    @InjectMocks
    private EditoraController editoraController;

    private MockMvc mockMvc;

    private EditoraDTOBuilder editoraDTOBuilder;

    @BeforeEach
    void setUp() {
        editoraDTOBuilder = EditoraDTOBuilder.builder().build();
        mockMvc = MockMvcBuilders.standaloneSetup(editoraController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }
}
