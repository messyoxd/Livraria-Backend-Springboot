package com.messyo.livraria.editora.service;

import com.messyo.livraria.editora.builder.EditoraDTOBuilder;
import com.messyo.livraria.editora.dto.EditoraDTO;
import com.messyo.livraria.editora.entity.Editora;
import com.messyo.livraria.editora.exception.EditoraAlreadyExistsException;
import com.messyo.livraria.editora.mapper.EditoraMapper;
import com.messyo.livraria.editora.repository.EditoraRepository;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @Test
    void whenNewEditoraIsInformedThenItShouldBeCreated() {
        //given
        EditoraDTO expectedEditoraToCreateDTO = editoraDTOBuilder.buildEditoraDTO();
        Editora expectedCreatedEditora = editoraMapper.toModel(expectedEditoraToCreateDTO);

        //when
        when(editoraRepository.save(expectedCreatedEditora)).thenReturn(expectedCreatedEditora);
        when(editoraRepository.findById(expectedCreatedEditora.getEditoraId())).thenReturn(Optional.empty());

        EditoraDTO createdEditoraDTO = editoraService.create(expectedEditoraToCreateDTO);

        //then
        assertThat(createdEditoraDTO, is(equalTo(expectedEditoraToCreateDTO)));
    }

    @Test
    void whenExistingEditoraIsInformedThenAnExceptionShouldThrown() {
        EditoraDTO expectedEditoraToCreateDTO = editoraDTOBuilder.buildEditoraDTO();
        Editora expectedCreatedEditora = editoraMapper.toModel(expectedEditoraToCreateDTO);

        when(editoraRepository.findById(expectedCreatedEditora.getEditoraId())).thenReturn(Optional.of(expectedCreatedEditora));

        assertThrows(EditoraAlreadyExistsException.class, () -> editoraService.create(expectedEditoraToCreateDTO));
    }
}
