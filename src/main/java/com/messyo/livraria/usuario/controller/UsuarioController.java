package com.messyo.livraria.usuario.controller;

import com.messyo.livraria.usuario.dto.UsuarioDTO;
import com.messyo.livraria.usuario.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController implements UsuarioControllerDocs{
    private UsuarioService _usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService){
        _usuarioService = usuarioService;
    }


//    @ApiOperation(value="Criar Usuario", authorizations = {@Authorization(value = "jwtToken")})
    @ApiOperation(value="Criar Usuario")
    @PostMapping
    public String create(@RequestBody @Valid UsuarioDTO usuarioDTO){
        return _usuarioService.create(usuarioDTO);
    }
}
