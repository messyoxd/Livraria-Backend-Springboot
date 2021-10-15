package com.messyo.livraria.editora.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.messyo.livraria.editora.builder.EditoraDTOBuilder;
import com.messyo.livraria.editora.dto.EditoraDTO;
import com.messyo.livraria.editora.service.EditoraService;
import com.messyo.livraria.utils.JsonConversionUtils;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static com.messyo.livraria.utils.JsonConversionUtils.*;
import static org.hamcrest.core.Is.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class EditoraControllerTest {

    private static final String EDITORAS_API_URL_PATH = "/api/v1/editoras";

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

    @Test
    void whenPOSTIsCalledThenStatusCreatedShouldBeReturned() throws Exception {

        EditoraDTO expectedCreatedEditoraDTO = editoraDTOBuilder.buildEditoraDTO();

        when(editoraService.create(expectedCreatedEditoraDTO))
                .thenReturn(expectedCreatedEditoraDTO);

        mockMvc.perform(post(EDITORAS_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(expectedCreatedEditoraDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.editoraId", is(expectedCreatedEditoraDTO.getEditoraId().intValue())))
                .andExpect(jsonPath("$.nome", is(expectedCreatedEditoraDTO.getNome())))
                .andExpect(jsonPath("$.cidade", is(expectedCreatedEditoraDTO.getCidade())));

    }

    @Test
    void whenPOSTIsCalledWithoutRequiredFieldThenBadRequestStatusShouldBeInformed() throws Exception {

        EditoraDTO expectedCreatedEditoraDTO = editoraDTOBuilder.buildEditoraDTO();
        expectedCreatedEditoraDTO.setNome(null);

        mockMvc.perform(post(EDITORAS_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(expectedCreatedEditoraDTO)))
                .andExpect(status().isBadRequest());

    }


}
