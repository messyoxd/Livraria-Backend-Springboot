package com.messyo.livraria.usuario.mapper;

import com.messyo.livraria.usuario.dto.UsuarioDTO;
import com.messyo.livraria.usuario.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {
//    fazer singleton @MapperAlgumacoisa
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    Usuario toModel(UsuarioDTO usuarioDTO);

    UsuarioDTO toDTO(Usuario usuario);

//    UsuarioViewModel toVM(Usuario usuario);
//
//    Usuario fromVM(UsuarioViewModel usuarioVM);
}
