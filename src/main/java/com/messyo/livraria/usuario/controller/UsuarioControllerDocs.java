package com.messyo.livraria.usuario.controller;

import com.messyo.livraria.usuario.dto.MessageDTO;
import com.messyo.livraria.usuario.dto.UsuarioDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api("Usuarios management")
public interface UsuarioControllerDocs {


    @ApiOperation(value = "Cadastrar usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success usuario creation"),
            @ApiResponse(code = 400, message = "Missing requered fields, wrong field range value or usuario already registered")
    })
    MessageDTO create(UsuarioDTO usuarioDTO);

    @ApiOperation(value = "Recuperar usuario por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success usuario found"),
            @ApiResponse(code = 404, message = "usuario not found")
    })
    UsuarioDTO findById(Long id);

    @ApiOperation(value = "Recuperar todos os usuarios")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success usuario found")
    })
    List<UsuarioDTO> getAll();

    @ApiOperation(value = "Remover usuario por id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success usuario deleted"),
            @ApiResponse(code = 404, message = "usuario not found")
    })
    Long removeById(Long id);

    @ApiOperation(value = "Editar usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success usuario edited"),
            @ApiResponse(code = 404, message = "usuario not found")
    })
    UsuarioDTO update(UsuarioDTO usuarioDTO);
}
