package com.messyo.livraria.editora.controller;

import com.messyo.livraria.editora.dto.EditoraDTO;
import com.messyo.livraria.editora.service.EditoraService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/editoras")
public class EditoraController implements EditoraControllerDocs {

    private EditoraService _editoraService;

    @Autowired
    public EditoraController(EditoraService editoraService) {
        _editoraService = editoraService;
    }

    @ApiOperation(value="Returns a hello world")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "Success method return")
    })
    @GetMapping
    public String helloWorld(){
        return "Hello World";
    }


//    @ApiOperation(value="Cadastrar editora", authorizations = {@Authorization(value = "jwtToken")})
    @ApiOperation(value="Cadastrar editora")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "Success method return")
    })
    @PostMapping
    public String create(@RequestBody @Valid EditoraDTO editoraDTO){
        return _editoraService.create(editoraDTO);
    }
}
