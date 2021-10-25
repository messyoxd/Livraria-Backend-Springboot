package com.messyo.livraria.livro.controller;

import com.messyo.livraria.livro.dto.LivroDTO;
import com.messyo.livraria.livro.viewmodel.LivroViewModel;
import io.swagger.annotations.*;

import java.util.List;

@Api(value = "Livros management")
public interface LivroControllerDocs {
    @ApiOperation(value = "Cadastrar livro", authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success livro creation"),
            @ApiResponse(code = 400, message = "Missing requered fields, wrong field range value or livro already registered")
    })
    LivroDTO create(LivroDTO livroDTO);

    @ApiOperation(value = "Recuperar todas as livros", authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success livro found")
    })
    List<LivroDTO> getAll();

    @ApiOperation(value = "Recuperar livro por id", authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success livro found"),
            @ApiResponse(code = 404, message = "Livro not found")
    })
    LivroDTO findById(Long id);

    @ApiOperation(value = "Remover livro por id", authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success livro deleted"),
            @ApiResponse(code = 404, message = "Livro not found")
    })
    Long removeById(Long id);

    @ApiOperation(value = "Editar livro", authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success livro edited"),
            @ApiResponse(code = 404, message = "Livro not found")
    })
    LivroDTO update(LivroViewModel vm);
}
