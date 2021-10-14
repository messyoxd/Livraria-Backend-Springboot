package com.messyo.livraria.emprestimo.mapper;

import com.messyo.livraria.emprestimo.dto.EmprestimoDTO;
import com.messyo.livraria.emprestimo.entity.Emprestimo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmprestimoMapper {
    EmprestimoMapper INSTANCE = Mappers.getMapper(EmprestimoMapper.class);

    Emprestimo toModel(EmprestimoDTO emprestimoDTO);

    EmprestimoDTO toDTO(Emprestimo emprestimo);
}