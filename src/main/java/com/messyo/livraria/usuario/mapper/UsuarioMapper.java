package com.messyo.livraria.usuario.mapper;

import com.messyo.livraria.usuario.dto.UsuarioDTO;
import com.messyo.livraria.usuario.entity.Usuario;
import com.messyo.livraria.usuario.viewmodel.UsuarioViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = MapperUtils.class)
public interface UsuarioMapper {

    @Mapping(target = "createdAt", expression = "java( MapperUtils.stringToLocalDateTime(usuarioDTO.getCreatedAt()) )")
    @Mapping(target = "updatedAt", expression = "java( MapperUtils.stringToLocalDateTime(usuarioDTO.getUpdatedAt()) )")
    Usuario toModel(UsuarioDTO usuarioDTO);

    @Mapping(target = "createdAt", expression = "java( MapperUtils.localDateTimeToString(usuario.getCreatedAt()) )")
    @Mapping(target = "updatedAt", expression = "java( MapperUtils.localDateTimeToString(usuario.getUpdatedAt()) )")
    UsuarioDTO toDTO(Usuario usuario);

    @Mapping(target = "createdAt", expression = "java( MapperUtils.localDateTimeToString(usuario.getCreatedAt()) )")
    @Mapping(target = "updatedAt", expression = "java( MapperUtils.localDateTimeToString(usuario.getUpdatedAt()) )")
    UsuarioViewModel toVM(Usuario usuario);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createdAt", expression = "java( MapperUtils.stringToLocalDateTime(vm.getCreatedAt()) )")
    @Mapping(target = "updatedAt", expression = "java( MapperUtils.stringToLocalDateTime(vm.getUpdatedAt()) )")
    Usuario vmToModel(UsuarioViewModel vm);

    @Mapping(target = "password", ignore = true)
    UsuarioDTO vmToDTO(UsuarioViewModel vm);

    UsuarioViewModel dtoToVm(UsuarioDTO dto);
}
