package com.messyo.livraria.editora.service;

import com.messyo.livraria.editora.builder.EditoraDTOBuilder;
import com.messyo.livraria.editora.dto.EditoraDTO;
import com.messyo.livraria.editora.mapper.EditoraMapper;
import com.messyo.livraria.editora.repository.EditoraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EditoraServiceTest {
    private final EditoraMapper editoraMapper = EditoraMapper.INSTANCE;

    @Mock
    private EditoraRepository editoraRepository;

    @InjectMocks
    private EditoraService editoraService;

    private EditoraDTOBuilder editoraDTOBuilder;

    @BeforeEach
    void setUp() {
        editoraDTOBuilder = EditoraDTOBuilder.builder().build();
//        EditoraDTO editoraDTO = editoraDTOBuilder.buildEditoraDTO();
    }
}
