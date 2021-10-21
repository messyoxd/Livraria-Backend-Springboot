package com.messyo.livraria.editora.controller;

import com.messyo.livraria.editora.dto.EditoraDTO;
import io.swagger.annotations.*;

import java.util.List;

@Api("Editoras management")
public interface EditoraControllerDocs {

    @ApiOperation(value = "Cadastrar editora", authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success editora creation"),
            @ApiResponse(code = 400, message = "Missing requered fields, wrong field range value or editora already registered")
    })
    EditoraDTO create(EditoraDTO editoraDTO);

    @ApiOperation(value = "Recuperar editora por id", authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success editora found"),
            @ApiResponse(code = 404, message = "Editora not found")
    })
    EditoraDTO findById(Long id);

    @ApiOperation(value = "Recuperar todas as editora", authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success editora found")
    })
    List<EditoraDTO> getAll();

    @ApiOperation(value = "Remover editora por id", authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success editora deleted"),
            @ApiResponse(code = 404, message = "Editora not found")
    })
    Long removeById(Long id);

    @ApiOperation(value = "Editar editora", authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success editora edited"),
            @ApiResponse(code = 404, message = "Editora not found")
    })
    EditoraDTO update(EditoraDTO editoraDTO);

}
