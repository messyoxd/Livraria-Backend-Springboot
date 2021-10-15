package com.messyo.livraria.livro.controller;

import com.messyo.livraria.livro.dto.LivroDTO;
import com.messyo.livraria.livro.service.LivroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/livros")
public class LivroController implements LivroControllerDocs{
    private LivroService _livroService;

    @Autowired
    public LivroController(LivroService livroService){
        _livroService = livroService;
    }

    @PostMapping
//    @ApiOperation(value="Criar Livro", authorizations = {@Authorization(value = "jwtToken")})
    @ApiOperation(value="Cadastrar livro")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "Success method return")
    })
    public String create(@RequestBody @Valid LivroDTO livroDTO){
        return _livroService.create(livroDTO);
    }
}
