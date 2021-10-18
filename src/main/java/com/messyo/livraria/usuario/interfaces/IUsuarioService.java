package com.messyo.livraria.usuario.interfaces;

import com.messyo.livraria.usuario.dto.MessageDTO;
import com.messyo.livraria.usuario.dto.UsuarioDTO;

import java.util.List;

public interface IUsuarioService {
    MessageDTO create(UsuarioDTO usuarioDTO);
    UsuarioDTO findById(Long id);
    List<UsuarioDTO> getAllUsuarios();
    UsuarioDTO updateUsuario(UsuarioDTO usuarioDTO);
    Long removeById(Long id);
}
