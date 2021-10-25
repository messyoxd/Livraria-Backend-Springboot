package com.messyo.livraria.usuario.service;

import com.messyo.livraria.livro.exception.UsuarioNotFoundException;
import com.messyo.livraria.usuario.dto.MessageDTO;
import com.messyo.livraria.usuario.dto.UsuarioDTO;
import com.messyo.livraria.usuario.entity.Usuario;
import com.messyo.livraria.usuario.interfaces.IUsuarioService;
import com.messyo.livraria.usuario.mapper.UsuarioMapper;
import com.messyo.livraria.usuario.repository.UsuarioRepository;
import com.messyo.livraria.usuario.viewmodel.UsuarioViewModel;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioService implements IUsuarioService {
    private final UsuarioRepository _usuarioRepository;

//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    private final PasswordEncoder passwordEncoder;

    protected UsuarioMapper _usuarioMapper;

//    @Override
//    public MessageDTO create(UsuarioDTO usuarioDTO) {
//        verifyIfExistsByEmail(usuarioDTO.getEmail());
//        Usuario usuarioToSave = _usuarioMapper.toModel(usuarioDTO);
//
//        usuarioToSave.setPassword(bCryptPasswordEncoder.encode(usuarioToSave.getPassword()));
//
//        Usuario savedUsuario = _usuarioRepository.save(usuarioToSave);
//        return MessageDTO.builder()
//                .message(String.format("User %s with ID %d was successfully created", savedUsuario.getNomeCompleto(), savedUsuario.getUsuarioId()))
//                .build();
//    }

//    private void verifyIfExistsByEmail(String email) {
//        _usuarioRepository.findByEmail(email)
//                .ifPresent(usuario -> {
//                    throw new UsuarioAlreadyExistsException(email);
//                });
//    }

    @Override
    public UsuarioViewModel findById(Long id) throws UsuarioNotFoundException {
        Usuario u = getUsuarioById(id);

        return _usuarioMapper.toVM(u);
    }

    private Usuario getUsuarioById(Long id) {
        Usuario u = _usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
        return u;
    }

    public List<UsuarioViewModel> getAllClients() {
        return _usuarioRepository.findAllClients().stream().map(_usuarioMapper::toVM).collect(Collectors.toList());
//        List<Usuario> u = _usuarioRepository.findAllClients();
//        List<UsuarioViewModel> uVM = new ArrayList<>();
//        for (Usuario us: u
//        ) {
//            uVM.add(_usuarioMapper.toVM(us));
//        }
//        return uVM;
    }

    @Override
    public List<UsuarioViewModel> getAllUsuarios() {
        return _usuarioRepository.findAll().stream().map(_usuarioMapper::toVM).collect(Collectors.toList());
    }

    @Override
    public MessageDTO updateUsuario(UsuarioViewModel usuarioVM) throws UsuarioNotFoundException {
        UsuarioDTO u = _usuarioMapper.toDTO(this.getUsuarioById(usuarioVM.getUsuarioId()));
        u.setNomeCompleto(StringUtils.isEmpty(usuarioVM.getNomeCompleto()) ? u.getNomeCompleto() : usuarioVM.getNomeCompleto());
        u.setCidade(StringUtils.isEmpty(usuarioVM.getCidade()) ? u.getCidade() : usuarioVM.getCidade());
        u.setEndereco(StringUtils.isEmpty(usuarioVM.getEndereco()) ? u.getEndereco() : usuarioVM.getEndereco());
//        u.getAppUser().setEmail(StringUtils.isEmpty(usuarioVM.getAppUser().getEmail()) ? u.getAppUser().getEmail() : usuarioVM.getAppUser().getEmail());
//        u.setUpdatedAt(new Date());
        Usuario usuarioToUpdate = _usuarioMapper.toModel(u);
        _usuarioRepository.save(usuarioToUpdate);
        return MessageDTO.builder()
                .message(String.format("User %s was successfully updated",usuarioToUpdate.getNomeCompleto()))
                .build();
    }

    @Override
    public Long removeById(Long id) throws UsuarioNotFoundException {
        UsuarioDTO u = _usuarioMapper.vmToDTO(this.findById(id));
        Usuario usuarioToRemove = _usuarioMapper.toModel(u);
        _usuarioRepository.delete(usuarioToRemove);
        return u.getUsuarioId();
    }
}

