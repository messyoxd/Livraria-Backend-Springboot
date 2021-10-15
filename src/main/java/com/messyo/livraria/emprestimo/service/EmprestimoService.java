package com.messyo.livraria.emprestimo.service;

import com.messyo.livraria.emprestimo.dto.EmprestimoDTO;
import com.messyo.livraria.emprestimo.entity.Emprestimo;
import com.messyo.livraria.emprestimo.exception.EmprestimoAlreadyExistsException;
import com.messyo.livraria.emprestimo.exception.EmprestimoNotFoundException;
import com.messyo.livraria.emprestimo.mapper.EmprestimoMapper;
import com.messyo.livraria.emprestimo.repository.EmprestimoRepository;
import com.messyo.livraria.livro.dto.LivroDTO;
import com.messyo.livraria.livro.mapper.LivroMapper;
import com.messyo.livraria.livro.service.LivroService;
import com.messyo.livraria.usuario.dto.UsuarioDTO;
import com.messyo.livraria.usuario.mapper.UsuarioMapper;
import com.messyo.livraria.usuario.service.UsuarioService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmprestimoService {
    private EmprestimoRepository _emprestimoRepository;
    private UsuarioService _usuarioService;
    private LivroService _livroService;

    private final EmprestimoMapper _emprestimoMapper = EmprestimoMapper.INSTANCE;
    private final LivroMapper _livroMapper = LivroMapper.INSTANCE;
    private final UsuarioMapper _usuarioMapper = UsuarioMapper.INSTANCE;

    @Autowired
    public EmprestimoService(EmprestimoRepository emprestimoRepository, UsuarioService usuarioService, LivroService livroService) {
        _livroService = livroService;
        _usuarioService = usuarioService;
        _emprestimoRepository = emprestimoRepository;
    }

    public EmprestimoDTO create(EmprestimoDTO emprestimoDTO) {
        verifyIfExists(emprestimoDTO.getEmprestimoId());
        _livroService.verifyIfLivroIsAvailable(emprestimoDTO.getLivroEmprestimo().getLivroId());
        Emprestimo emprestimoToSave = _emprestimoMapper.toModel(emprestimoDTO);
        Emprestimo savedEmprestimo = _emprestimoRepository.save(emprestimoToSave);
        _livroService.decrementarQuantidade(emprestimoDTO.getLivroEmprestimo().getLivroId(), emprestimoDTO.getLivroEmprestimo().getQuantidadeDisponivel());
        return _emprestimoMapper.toDTO(savedEmprestimo);
    }

    private void verifyIfExists(Long id) {
        _emprestimoRepository.findById(id)
                .ifPresent(emprestimo -> {
                    throw new EmprestimoAlreadyExistsException(emprestimo.getEmprestimoId());
                });
    }

    private void verifyIfDoesNotExists(Long id) {
        _emprestimoRepository.findById(id).orElseThrow(() -> new EmprestimoNotFoundException(id));
    }

    public EmprestimoDTO findById(Long id) throws EmprestimoNotFoundException {
        Emprestimo e = _emprestimoRepository.findById(id).orElseThrow(() -> new EmprestimoNotFoundException(id));

        return _emprestimoMapper.toDTO(e);
    }

    public List<EmprestimoDTO> findByUsuarioId(long usuarioId) {
        UsuarioDTO u = _usuarioService.findById(usuarioId);
        List<Emprestimo> e = _emprestimoRepository.findByUsuarioEmprestimo(_usuarioMapper.toModel(u));
        List<EmprestimoDTO> eDTO = new ArrayList<>();
        for (Emprestimo em : e) {
            eDTO.add(_emprestimoMapper.toDTO(em));
        }
        return eDTO;
    }

    public List<EmprestimoDTO> findByLivroId(long livroId) {
        LivroDTO l = _livroService.findById(livroId);
        List<Emprestimo> e = _emprestimoRepository.findByLivroEmprestimo(_livroMapper.toModel(l));
        List<EmprestimoDTO> eDTO = new ArrayList<>();
        for (Emprestimo em : e) {
            eDTO.add(_emprestimoMapper.toDTO(em));
        }
        return eDTO;
    }

    public List<EmprestimoDTO> findByUsuarioIdAndLivroId(long usuarioId, long livroId) {
        UsuarioDTO u = _usuarioService.findById(usuarioId);
        LivroDTO l = _livroService.findById(livroId);
        List<Emprestimo> e = _emprestimoRepository.findByLivroEmprestimoAndUsuarioEmprestimo(_usuarioMapper.toModel(u), _livroMapper.toModel(l));
        List<EmprestimoDTO> eDTO = new ArrayList<>();
        for (Emprestimo em : e) {
            eDTO.add(_emprestimoMapper.toDTO(em));
        }
        return eDTO;
    }

    public List<EmprestimoDTO> getAll() {
        return _emprestimoRepository.findAll().stream().map(_emprestimoMapper::toDTO).collect(Collectors.toList());
    }

    public EmprestimoDTO updateEmprestimo(EmprestimoDTO emprestimoDTO) throws EmprestimoNotFoundException {
        EmprestimoDTO e = this.findById(emprestimoDTO.getEmprestimoId());
        e.setLivroEmprestimo(emprestimoDTO.getLivroEmprestimo() == null ? e.getLivroEmprestimo() : emprestimoDTO.getLivroEmprestimo());
        e.setUsuarioEmprestimo(emprestimoDTO.getUsuarioEmprestimo() == null ? e.getUsuarioEmprestimo() : emprestimoDTO.getUsuarioEmprestimo());
        e.setPrevisaoDevolucao(emprestimoDTO.getPrevisaoDevolucao() == null ? e.getPrevisaoDevolucao() : emprestimoDTO.getPrevisaoDevolucao());
        e.setDataDevolucao(emprestimoDTO.getDataDevolucao() == null ? e.getDataDevolucao() : emprestimoDTO.getDataDevolucao());
        e.setStatusEmprestimo(StringUtils.isEmpty(emprestimoDTO.getStatusEmprestimo()) ? e.getStatusEmprestimo() : emprestimoDTO.getStatusEmprestimo());
        Emprestimo emprestimoToUpdate = _emprestimoMapper.toModel(e);
        _emprestimoRepository.save(emprestimoToUpdate);
        return e;
    }

    public Long devolucaoDeLivro(EmprestimoDTO emprestimo) {
        verifyIfDoesNotExists(emprestimo.getEmprestimoId());
        if (emprestimo.getDataDevolucao().plusDays(1).isAfter(emprestimo.getPrevisaoDevolucao())) {
            emprestimo.setStatusEmprestimo("Devolvido com atraso");
        } else {
            emprestimo.setStatusEmprestimo("Devolvido");
        }
        Emprestimo emprestimoToUpdate = _emprestimoMapper.toModel(emprestimo);
        _emprestimoRepository.save(emprestimoToUpdate);
        return emprestimo.getEmprestimoId();
    }

    public Long removeById(Long id) throws EmprestimoNotFoundException {
        EmprestimoDTO e = this.findById(id);
        Emprestimo emprestimoToRemove = _emprestimoMapper.toModel(e);
        _emprestimoRepository.delete(emprestimoToRemove);
        return e.getEmprestimoId();
    }
}
