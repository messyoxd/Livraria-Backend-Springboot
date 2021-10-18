package com.messyo.livraria.emprestimo.service;

import com.messyo.livraria.emprestimo.dto.EmprestimoDTO;
import com.messyo.livraria.emprestimo.entity.Emprestimo;
import com.messyo.livraria.emprestimo.exception.EmprestimoNotFoundException;
import com.messyo.livraria.emprestimo.interfaces.IEmprestimoService;
import com.messyo.livraria.emprestimo.mapper.EmprestimoMapper;
import com.messyo.livraria.emprestimo.repository.EmprestimoRepository;
import com.messyo.livraria.livro.dto.LivroDTO;
import com.messyo.livraria.livro.interfaces.ILivroService;
import com.messyo.livraria.livro.mapper.LivroMapper;
import com.messyo.livraria.usuario.dto.UsuarioDTO;
import com.messyo.livraria.usuario.interfaces.IUsuarioService;
import com.messyo.livraria.usuario.mapper.UsuarioMapper;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmprestimoService implements IEmprestimoService {
    private EmprestimoRepository _emprestimoRepository;

    @Autowired
    protected IUsuarioService _usuarioService;

    @Autowired
    protected ILivroService _livroService;

    @Autowired
    protected EmprestimoMapper _emprestimoMapper;

    @Autowired
    protected LivroMapper _livroMapper;

    @Autowired
    protected UsuarioMapper _usuarioMapper;

    @Autowired
    public EmprestimoService(EmprestimoRepository emprestimoRepository) {
        _emprestimoRepository = emprestimoRepository;
    }

    @Override
    public EmprestimoDTO create(EmprestimoDTO emprestimoDTO) {
//        verifyIfExists(emprestimoDTO.getEmprestimoId());
        int quantidade = _livroService.verifyIfLivroIsAvailable(emprestimoDTO.getLivroEmprestimo().getLivroId());
        Emprestimo emprestimoToSave = _emprestimoMapper.toModel(emprestimoDTO);
        Emprestimo savedEmprestimo = _emprestimoRepository.save(emprestimoToSave);
        _livroService.decrementarQuantidade(emprestimoDTO.getLivroEmprestimo().getLivroId(), quantidade);
        return _emprestimoMapper.toDTO(savedEmprestimo);
    }

//    private void verifyIfExists(Long id) {
//        _emprestimoRepository.findById(id)
//                .ifPresent(emprestimo -> {
//                    throw new EmprestimoAlreadyExistsException(emprestimo.getEmprestimoId());
//                });
//    }

    @Override
    public void verifyIfDoesNotExists(Long id) {
        _emprestimoRepository.findById(id).orElseThrow(() -> new EmprestimoNotFoundException(id));
    }

    @Override
    public EmprestimoDTO findById(Long id) throws EmprestimoNotFoundException {
        Emprestimo e = _emprestimoRepository.findById(id).orElseThrow(() -> new EmprestimoNotFoundException(id));

        return _emprestimoMapper.toDTO(e);
    }

    @Override
    public List<EmprestimoDTO> findByUsuarioId(long usuarioId) {
        UsuarioDTO u = _usuarioService.findById(usuarioId);
        List<Emprestimo> e = _emprestimoRepository.findByUsuarioEmprestimo(_usuarioMapper.toModel(u));
        List<EmprestimoDTO> eDTO = new ArrayList<>();
        for (Emprestimo em : e) {
            eDTO.add(_emprestimoMapper.toDTO(em));
        }
        return eDTO;
    }

    @Override
    public List<EmprestimoDTO> findByLivroId(long livroId) {
        LivroDTO l = _livroService.findById(livroId);
        List<Emprestimo> e = _emprestimoRepository.findByLivroEmprestimo(_livroMapper.toModel(l));
        List<EmprestimoDTO> eDTO = new ArrayList<>();
        for (Emprestimo em : e) {
            eDTO.add(_emprestimoMapper.toDTO(em));
        }
        return eDTO;
    }

    @Override
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

    @Override
    public List<EmprestimoDTO> getAll() {
        return _emprestimoRepository.findAll().stream().map(_emprestimoMapper::toDTO).collect(Collectors.toList());
    }

    @Override
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

//
    private LocalDateTime convertToLocalDateTimeViaSqlTimestamp(Date dateToConvert) {
        return new java.sql.Timestamp(
                dateToConvert.getTime()).toLocalDateTime();
    }

    @Override
    public Long devolucaoDeLivro(EmprestimoDTO emprestimo) {
        verifyIfDoesNotExists(emprestimo.getEmprestimoId());
//        LocalDateTime dataDevolucao = convertToLocalDateTimeViaSqlTimestamp(emprestimo.getDataDevolucao());
//        LocalDateTime previsaoDevolucao = convertToLocalDateTimeViaSqlTimestamp(emprestimo.getPrevisaoDevolucao());
        if (emprestimo.getDataDevolucao().plusDays(1).isAfter(emprestimo.getPrevisaoDevolucao())) {
            emprestimo.setStatusEmprestimo("Devolvido com atraso");
        } else {
            emprestimo.setStatusEmprestimo("Devolvido");
        }
        Emprestimo emprestimoToUpdate = _emprestimoMapper.toModel(emprestimo);
        _emprestimoRepository.save(emprestimoToUpdate);
        return emprestimo.getEmprestimoId();
    }

    @Override
    public Long removeById(Long id) throws EmprestimoNotFoundException {
        EmprestimoDTO e = this.findById(id);
        Emprestimo emprestimoToRemove = _emprestimoMapper.toModel(e);
        _emprestimoRepository.delete(emprestimoToRemove);
        return e.getEmprestimoId();
    }
}
