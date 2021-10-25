package com.messyo.livraria.emprestimo.mapper;

import com.messyo.livraria.emprestimo.dto.EmprestimoDTO;
import com.messyo.livraria.emprestimo.entity.Emprestimo;
import com.messyo.livraria.emprestimo.viewmodel.EmprestimoViewmodel;
import com.messyo.livraria.usuario.dto.UsuarioDTO;
import com.messyo.livraria.usuario.entity.Usuario;
import com.messyo.livraria.usuario.viewmodel.UsuarioViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = MapperUtils.class)
public interface EmprestimoMapper {
    EmprestimoMapper INSTANCE = Mappers.getMapper(EmprestimoMapper.class);

    @Mapping(target = "createdAt", expression = "java( MapperUtils.stringToLocalDateTime(emprestimoDTO.getCreatedAt()) )")
    @Mapping(target = "updatedAt", expression = "java( MapperUtils.stringToLocalDateTime(emprestimoDTO.getUpdatedAt()) )")
    Emprestimo toModel(EmprestimoDTO emprestimoDTO);

    @Mapping(target = "createdAt", expression = "java( MapperUtils.localDateTimeToString(emprestimo.getCreatedAt()) )")
    @Mapping(target = "updatedAt", expression = "java( MapperUtils.localDateTimeToString(emprestimo.getUpdatedAt()) )")
    EmprestimoDTO toDTO(Emprestimo emprestimo);

    @Mapping(target = "createdAt", expression = "java( MapperUtils.localDateTimeToString(emprestimo.getCreatedAt()) )")
    @Mapping(target = "updatedAt", expression = "java( MapperUtils.localDateTimeToString(emprestimo.getUpdatedAt()) )")
    EmprestimoViewmodel toVM(Emprestimo emprestimo);

    @Mapping(target = "createdAt", expression = "java( MapperUtils.stringToLocalDateTime(vm.getCreatedAt()) )")
    @Mapping(target = "updatedAt", expression = "java( MapperUtils.stringToLocalDateTime(vm.getUpdatedAt()) )")
    Emprestimo vmToModel(EmprestimoViewmodel vm);

    EmprestimoDTO vmToDTO(EmprestimoViewmodel vm);

    EmprestimoViewmodel dtoToVm(EmprestimoDTO dto);
}