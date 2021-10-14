package com.messyo.livraria.usuario.service;

import com.messyo.livraria.usuario.dto.UsuarioDTO;
import com.messyo.livraria.usuario.entity.Usuario;
import com.messyo.livraria.usuario.mapper.UsuarioMapper;
import com.messyo.livraria.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository _usuarioRepository;

    private final UsuarioMapper _usuarioMapper = UsuarioMapper.INSTANCE;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository){
        _usuarioRepository = usuarioRepository;
    }


    public String create(UsuarioDTO usuarioDTO) {
        Usuario usuarioToSave = _usuarioMapper.toModel(usuarioDTO);
        Usuario savedUsuario = null;
        try {
            savedUsuario = _usuarioRepository.save(usuarioToSave);
        }catch (DataIntegrityViolationException e){
            return "Email já utilizado!";
        }catch (Exception e){
            System.out.println("*************************************");
            System.out.println(e);
            System.out.println("*************************************");
            return "Erro no sistema!";
        }
        return "Usuario criado com id " + savedUsuario.getUsuarioId();
    }

//    public UsuarioDTO findById(Long id) throws UsuarioNotFoundException {
//        Usuario u = _usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
//
//        return _usuarioMapper.toDTO(u);
//    }
//
//    public List<UsuarioViewModel> getAllClients() {
//        List<Usuario> u = _usuarioRepository.findAllClients();
//        List<UsuarioViewModel> uVM = new ArrayList<>();
//        for (Usuario us: u
//        ) {
//            uVM.add(_usuarioMapper.toVM(us));
//        }
//        return uVM;
//    }
//
//    public List<UsuarioViewModel> getAllUsuarios() {
//        List<Usuario> u = _usuarioRepository.findAll();
//        List<UsuarioViewModel> uVM = new ArrayList<>();
//        for (Usuario us: u
//        ) {
//            uVM.add(_usuarioMapper.toVM(us));
//        }
//        return uVM;
//    }
//
//    public UsuarioDTO updateUsuario(UsuarioViewModel usuarioVM) throws UsuarioNotFoundException {
//        UsuarioDTO u = this.findById(usuarioVM.getUsuarioId());
//        u.setNomeCompleto(StringUtils.isEmpty(usuarioVM.getNomeCompleto()) ? u.getNomeCompleto() : usuarioVM.getNomeCompleto());
//        u.setCidade(StringUtils.isEmpty(usuarioVM.getCidade()) ? u.getCidade() : usuarioVM.getCidade());
//        u.setEndereco(StringUtils.isEmpty(usuarioVM.getEndereco()) ? u.getEndereco() : usuarioVM.getEndereco());
//        u.getAppUser().setEmail(StringUtils.isEmpty(usuarioVM.getAppUser().getEmail()) ? u.getAppUser().getEmail() : usuarioVM.getAppUser().getEmail());
//        u.setUpdatedAt(new Date());
//        Usuario usuarioToUpdate = _usuarioMapper.toModel(u);
//        _usuarioRepository.save(usuarioToUpdate);
//        return u;
//    }
//
//    public Long removeById(Long id) throws UsuarioNotFoundException {
//        UsuarioDTO u = this.findById(id);
//        Usuario usuarioToRemove = _usuarioMapper.toModel(u);
//        _usuarioRepository.delete(usuarioToRemove);
//        return u.getUsuarioId();
//    }
}

