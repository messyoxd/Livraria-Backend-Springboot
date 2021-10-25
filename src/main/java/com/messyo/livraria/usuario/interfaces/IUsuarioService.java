package com.messyo.livraria.usuario.interfaces;

import com.messyo.livraria.usuario.dto.MessageDTO;
import com.messyo.livraria.usuario.dto.UsuarioDTO;
import com.messyo.livraria.usuario.viewmodel.UsuarioViewModel;

import java.util.List;

public interface IUsuarioService {
//    MessageDTO create(UsuarioDTO usuarioDTO);
    UsuarioViewModel findById(Long id);
    List<UsuarioViewModel> getAllUsuarios();
    MessageDTO updateUsuario(UsuarioViewModel usuarioVM);
    Long removeById(Long id);
    List<UsuarioViewModel> getAllClients();
}
