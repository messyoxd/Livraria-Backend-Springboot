package com.messyo.livraria.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/editoras")
public class EditoraController {

    @GetMapping
    public String helloWorld(){
        return "Hello World";
    }
}
