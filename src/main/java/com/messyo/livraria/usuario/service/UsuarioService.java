package com.messyo.livraria.usuario.service;

import com.messyo.livraria.livro.exception.UsuarioAlreadyExistsException;
import com.messyo.livraria.livro.exception.UsuarioNotFoundException;
import com.messyo.livraria.usuario.dto.MessageDTO;
import com.messyo.livraria.usuario.dto.UsuarioDTO;
import com.messyo.livraria.usuario.entity.Usuario;
import com.messyo.livraria.usuario.interfaces.IUsuarioService;
import com.messyo.livraria.usuario.mapper.UsuarioMapper;
import com.messyo.livraria.usuario.repository.UsuarioRepository;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService {
    private final UsuarioRepository _usuarioRepository;

    protected PasswordEncoder _passwordEncoder;

    @Autowired
    protected UsuarioMapper _usuarioMapper;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        _usuarioRepository = usuarioRepository;
        _passwordEncoder = passwordEncoder;
    }


    @Override
    public MessageDTO create(UsuarioDTO usuarioDTO) {
        verifyIfExistsByEmail(usuarioDTO.getEmail());
        Usuario usuarioToSave = _usuarioMapper.toModel(usuarioDTO);

        usuarioToSave.setPassword(_passwordEncoder.encode(usuarioToSave.getPassword()));

        Usuario savedUsuario = _usuarioRepository.save(usuarioToSave);
        return MessageDTO.builder()
                .message(String.format("User %s with ID %d was successfully created", savedUsuario.getNomeCompleto(), savedUsuario.getUsuarioId()))
                .build();
    }

    private void verifyIfExistsByEmail(String email) {
        _usuarioRepository.findByEmail(email)
                .ifPresent(usuario -> {
                    throw new UsuarioAlreadyExistsException(email);
                });
    }

    @Override
    public UsuarioDTO findById(Long id) throws UsuarioNotFoundException {
        Usuario u = _usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));

        return _usuarioMapper.toDTO(u);
    }

//    public List<UsuarioViewModel> getAllClients() {
//        List<Usuario> u = _usuarioRepository.findAllClients();
//        List<UsuarioViewModel> uVM = new ArrayList<>();
//        for (Usuario us: u
//        ) {
//            uVM.add(_usuarioMapper.toVM(us));
//        }
//        return uVM;
//    }

    @Override
    public List<UsuarioDTO> getAllUsuarios() {
        return _usuarioRepository.findAll().stream().map(_usuarioMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO updateUsuario(UsuarioDTO usuarioDTO) throws UsuarioNotFoundException {
        UsuarioDTO u = this.findById(usuarioDTO.getUsuarioId());
        u.setNomeCompleto(StringUtils.isEmpty(usuarioDTO.getNomeCompleto()) ? u.getNomeCompleto() : usuarioDTO.getNomeCompleto());
        u.setCidade(StringUtils.isEmpty(usuarioDTO.getCidade()) ? u.getCidade() : usuarioDTO.getCidade());
        u.setEndereco(StringUtils.isEmpty(usuarioDTO.getEndereco()) ? u.getEndereco() : usuarioDTO.getEndereco());
//        u.getAppUser().setEmail(StringUtils.isEmpty(usuarioVM.getAppUser().getEmail()) ? u.getAppUser().getEmail() : usuarioVM.getAppUser().getEmail());
//        u.setUpdatedAt(new Date());
        Usuario usuarioToUpdate = _usuarioMapper.toModel(u);
        _usuarioRepository.save(usuarioToUpdate);
        return u;
    }

    @Override
    public Long removeById(Long id) throws UsuarioNotFoundException {
        UsuarioDTO u = this.findById(id);
        Usuario usuarioToRemove = _usuarioMapper.toModel(u);
        _usuarioRepository.delete(usuarioToRemove);
        return u.getUsuarioId();
    }
}

