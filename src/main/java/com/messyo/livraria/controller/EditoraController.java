package com.messyo.livraria.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/editoras")
public class EditoraController {

    @ApiOperation(value="Returns a hello world")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "Success method return")
    })
    @GetMapping
    public String helloWorld(){
        return "Hello World";
    }
}
