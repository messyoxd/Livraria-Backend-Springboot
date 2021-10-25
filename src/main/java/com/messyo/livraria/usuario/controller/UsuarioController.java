package com.messyo.livraria.usuario.controller;

import com.messyo.livraria.usuario.dto.JwtRequest;
import com.messyo.livraria.usuario.dto.JwtResponse;
import com.messyo.livraria.usuario.dto.MessageDTO;
import com.messyo.livraria.usuario.dto.UsuarioDTO;
import com.messyo.livraria.usuario.interfaces.IUsuarioService;
import com.messyo.livraria.usuario.service.AuthenticationService;
import com.messyo.livraria.usuario.viewmodel.UsuarioViewModel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioController implements UsuarioControllerDocs {

    private IUsuarioService _usuarioService;

    private final AuthenticationService authenticationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDTO create(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        return authenticationService.signUp(usuarioDTO);
    }

    @PostMapping(value = "/authenticate")
    public JwtResponse createAuthenticationToken(@RequestBody @Valid JwtRequest jwtRequest) {
        return authenticationService.createAuthenticationToken(jwtRequest);
    }

    @GetMapping("/{id}")
    public UsuarioViewModel findById(@PathVariable Long id) {
        return _usuarioService.findById(id);
    }

    @GetMapping("/clients")
    public List<UsuarioViewModel> getAllClients(){
        return _usuarioService.getAllClients();

    }

    @GetMapping("/")
    public List<UsuarioViewModel> getAll() {
        return _usuarioService.getAllUsuarios();
    }

    @DeleteMapping("/{id}")
    public Long removeById(@PathVariable Long id) {
        return _usuarioService.removeById(id);
    }

    @PutMapping
    public MessageDTO update(@RequestBody @Valid UsuarioViewModel usuarioVM) {
        return _usuarioService.updateUsuario(usuarioVM);
    }
}
