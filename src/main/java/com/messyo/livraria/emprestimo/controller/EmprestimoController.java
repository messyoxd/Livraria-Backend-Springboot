package com.messyo.livraria.emprestimo.controller;

import com.messyo.livraria.emprestimo.dto.EmprestimoDTO;
import com.messyo.livraria.emprestimo.service.EmprestimoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/emprestimos")
public class EmprestimoController implements EmprestimoControllerDocs{
    private EmprestimoService _emprestimoService;

    @Autowired
    public EmprestimoController(EmprestimoService emprestimoService){
        _emprestimoService = emprestimoService;
    }


//    @ApiOperation(value="Criar Emprestimo", authorizations = {@Authorization(value = "jwtToken")})
    @ApiOperation(value="Criar Emprestimo")
    @PostMapping
    public String create(@RequestBody @Valid EmprestimoDTO emprestimoDTO){
        return _emprestimoService.create(emprestimoDTO);
    }
}
