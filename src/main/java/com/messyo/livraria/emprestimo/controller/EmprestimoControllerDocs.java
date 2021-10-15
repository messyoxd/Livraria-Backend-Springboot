package com.messyo.livraria.emprestimo.controller;

import com.messyo.livraria.emprestimo.dto.EmprestimoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Api("Emprestimos management")
public interface EmprestimoControllerDocs {
    @ApiOperation(value = "Cadastrar emprestimo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success emprestimo creation"),
            @ApiResponse(code = 400, message = "Missing requered fields, wrong field range value or emprestimo already registered")
    })
    EmprestimoDTO create(EmprestimoDTO emprestimoDTO);

    @ApiOperation(value = "Recuperar emprestimo por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success emprestimo found"),
            @ApiResponse(code = 404, message = "emprestimo not found")
    })
    EmprestimoDTO findById(Long id);

    @ApiOperation(value = "Recuperar todos os emprestimos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success emprestimo found")
    })
    List<EmprestimoDTO> getAll();

    @ApiOperation(value = "Recuperar emprestimo pelo usuarioid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success emprestimo found"),
            @ApiResponse(code = 404, message = "emprestimo not found")
    })
    List<EmprestimoDTO> findByUsuarioId(Long id);

    @ApiOperation(value = "Recuperar emprestimo pelo livroid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success emprestimo found"),
            @ApiResponse(code = 404, message = "emprestimo not found")
    })
    List<EmprestimoDTO> findByLivroId(Long id);

    @ApiOperation(value = "Recuperar emprestimo pelo usuarioid e livroid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success emprestimo found"),
            @ApiResponse(code = 404, message = "emprestimo not found")
    })
    List<EmprestimoDTO> findByUsuarioIdAndLivroId(Long usuarioId, Long livroId);

    @ApiOperation(value = "Remover emprestimo por id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success emprestimo deleted"),
            @ApiResponse(code = 404, message = "Editora not found")
    })
    Long removeById(Long id);

    @ApiOperation(value = "Editar emprestimo")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success emprestimo edited"),
            @ApiResponse(code = 404, message = "emprestimo not found")
    })
    EmprestimoDTO update(EmprestimoDTO emprestimoDTO);

    @ApiOperation(value = "Devolver livro")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success emprestimo edited"),
            @ApiResponse(code = 404, message = "emprestimo not found")
    })
    Long devolucao(EmprestimoDTO emprestimoDTO);

}
