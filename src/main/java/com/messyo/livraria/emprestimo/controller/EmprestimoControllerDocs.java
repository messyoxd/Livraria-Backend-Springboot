package com.messyo.livraria.emprestimo.controller;

import com.messyo.livraria.emprestimo.dto.EmprestimoDTO;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Api("Emprestimos management")
public interface EmprestimoControllerDocs {
    @ApiOperation(value = "Cadastrar emprestimo", authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success emprestimo creation"),
            @ApiResponse(code = 400, message = "Missing requered fields, wrong field range value or emprestimo already registered")
    })
    EmprestimoDTO create(EmprestimoDTO emprestimoDTO);

    @ApiOperation(value = "Recuperar emprestimo por id", authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success emprestimo found"),
            @ApiResponse(code = 404, message = "emprestimo not found")
    })
    EmprestimoDTO findById(Long id);

    @ApiOperation(value = "Recuperar todos os emprestimos", authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success emprestimo found")
    })
    List<EmprestimoDTO> getAll();

    @ApiOperation(value = "Recuperar emprestimo pelo usuarioid", authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success emprestimo found"),
            @ApiResponse(code = 404, message = "emprestimo not found")
    })
    List<EmprestimoDTO> findByUsuarioId(Long id);

    @ApiOperation(value = "Recuperar emprestimo pelo livroid", authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success emprestimo found"),
            @ApiResponse(code = 404, message = "emprestimo not found")
    })
    List<EmprestimoDTO> findByLivroId(Long id);

    @ApiOperation(value = "Recuperar emprestimo pelo usuarioid e livroid", authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success emprestimo found"),
            @ApiResponse(code = 404, message = "emprestimo not found")
    })
    List<EmprestimoDTO> findByUsuarioIdAndLivroId(Long usuarioId, Long livroId);

    @ApiOperation(value = "Remover emprestimo por id", authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success emprestimo deleted"),
            @ApiResponse(code = 404, message = "Editora not found")
    })
    Long removeById(Long id);

    @ApiOperation(value = "Editar emprestimo", authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success emprestimo edited"),
            @ApiResponse(code = 404, message = "emprestimo not found")
    })
    EmprestimoDTO update(EmprestimoDTO emprestimoDTO);

    @ApiOperation(value = "Devolver livro", authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success emprestimo edited"),
            @ApiResponse(code = 404, message = "emprestimo not found")
    })
    Long devolucao(EmprestimoDTO emprestimoDTO);

}
