package com.messyo.livraria.livro.mapper;

import com.messyo.livraria.livro.dto.LivroDTO;
import com.messyo.livraria.livro.entity.Livro;
import com.messyo.livraria.livro.viewmodel.LivroViewModel;
import com.messyo.livraria.usuario.mapper.UsuarioMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {MapperUtils.class, LivroMapper.class, UsuarioMapper.class})
public interface LivroMapper {
    LivroMapper INSTANCE = Mappers.getMapper(LivroMapper.class);

    @Mapping(target = "createdAt", expression = "java( MapperUtils.stringToLocalDateTime(livroDTO.getCreatedAt()) )")
    @Mapping(target = "updatedAt", expression = "java( MapperUtils.stringToLocalDateTime(livroDTO.getUpdatedAt()) )")
    Livro toModel(LivroDTO livroDTO);

    @Mapping(target = "createdAt", expression = "java( MapperUtils.localDateTimeToString(livro.getCreatedAt()) )")
    @Mapping(target = "updatedAt", expression = "java( MapperUtils.localDateTimeToString(livro.getUpdatedAt()) )")
    LivroDTO toDTO(Livro livro);

    @Mapping(target = "createdAt", expression = "java( MapperUtils.localDateTimeToString(livro.getCreatedAt()) )")
    @Mapping(target = "updatedAt", expression = "java( MapperUtils.localDateTimeToString(livro.getUpdatedAt()) )")
    LivroViewModel toVM(Livro livro);

    @Mapping(target = "createdAt", expression = "java( MapperUtils.stringToLocalDateTime(vm.getCreatedAt()) )")
    @Mapping(target = "updatedAt", expression = "java( MapperUtils.stringToLocalDateTime(vm.getUpdatedAt()) )")
    Livro vmToModel(LivroViewModel vm);

    LivroDTO vmToDTO(LivroViewModel vm);

    LivroViewModel dtoToVm(LivroDTO dto);
}
