package com.messyo.livraria.editora.mapper;

import com.messyo.livraria.editora.dto.EditoraDTO;
import com.messyo.livraria.editora.entity.Editora;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EditoraMapper {
    EditoraMapper INSTANCE = Mappers.getMapper(EditoraMapper.class);

    Editora toModel(EditoraDTO editoraDTO);

    EditoraDTO toDTO(Editora editora);
}
