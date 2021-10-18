package com.messyo.livraria.usuario.controller;

import com.messyo.livraria.usuario.dto.MessageDTO;
import com.messyo.livraria.usuario.dto.UsuarioDTO;
import com.messyo.livraria.usuario.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController implements UsuarioControllerDocs {

    @Autowired
    private IUsuarioService _usuarioService;

    //    @ApiOperation(value="Criar Usuario", authorizations = {@Authorization(value = "jwtToken")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDTO create(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        return _usuarioService.create(usuarioDTO);
    }

    @GetMapping("/{id}")
    public UsuarioDTO findById(@PathVariable Long id) {
        return _usuarioService.findById(id);
    }

//    @GetMapping("/clients")
//    public List<UsuarioViewModel> getAllClients(){
//        return _usuarioService.getAllClients();
//    }

    @GetMapping("/")
    public List<UsuarioDTO> getAll() {
        return _usuarioService.getAllUsuarios();
    }

    @DeleteMapping("/{id}")
    public Long removeById(@PathVariable Long id) {
        return _usuarioService.removeById(id);
    }

    @PutMapping
    public UsuarioDTO update(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        return _usuarioService.updateUsuario(usuarioDTO);
    }
}
