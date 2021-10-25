package com.messyo.livraria.editora.mapper;

import com.messyo.livraria.editora.dto.EditoraDTO;
import com.messyo.livraria.editora.entity.Editora;
import com.messyo.livraria.editora.viewmodel.EditoraViewModel;
import com.messyo.livraria.usuario.dto.UsuarioDTO;
import com.messyo.livraria.usuario.entity.Usuario;
import com.messyo.livraria.usuario.mapper.MapperUtils;
import com.messyo.livraria.usuario.viewmodel.UsuarioViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = MapperUtils.class)
public interface EditoraMapper {
    EditoraMapper INSTANCE = Mappers.getMapper(EditoraMapper.class);


    @Mapping(target = "createdAt", expression = "java( MapperUtils.stringToLocalDateTime(editoraDTO.getCreatedAt()) )")
    @Mapping(target = "updatedAt", expression = "java( MapperUtils.stringToLocalDateTime(editoraDTO.getUpdatedAt()) )")
    Editora toModel(EditoraDTO editoraDTO);

    @Mapping(target = "createdAt", expression = "java( MapperUtils.localDateTimeToString(editora.getCreatedAt()) )")
    @Mapping(target = "updatedAt", expression = "java( MapperUtils.localDateTimeToString(editora.getUpdatedAt()) )")
    EditoraDTO toDTO(Editora editora);

    @Mapping(target = "createdAt", expression = "java( MapperUtils.localDateTimeToString(editora.getCreatedAt()) )")
    @Mapping(target = "updatedAt", expression = "java( MapperUtils.localDateTimeToString(editora.getUpdatedAt()) )")
    EditoraViewModel toVM(Editora editora);

    @Mapping(target = "createdAt", expression = "java( MapperUtils.stringToLocalDateTime(vm.getCreatedAt()) )")
    @Mapping(target = "updatedAt", expression = "java( MapperUtils.stringToLocalDateTime(vm.getUpdatedAt()) )")
    Editora vmToModel(EditoraViewModel vm);

    EditoraDTO vmToDTO(EditoraViewModel vm);

    EditoraViewModel dtoToVm(EditoraDTO dto);
}
