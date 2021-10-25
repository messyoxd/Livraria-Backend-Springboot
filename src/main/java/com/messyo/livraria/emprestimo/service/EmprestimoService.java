package com.messyo.livraria.emprestimo.service;

import com.messyo.livraria.emprestimo.dto.EmprestimoDTO;
import com.messyo.livraria.emprestimo.entity.Emprestimo;
import com.messyo.livraria.emprestimo.exception.EmprestimoNotFoundException;
import com.messyo.livraria.emprestimo.interfaces.IEmprestimoService;
import com.messyo.livraria.emprestimo.mapper.EmprestimoMapper;
import com.messyo.livraria.emprestimo.repository.EmprestimoRepository;
import com.messyo.livraria.emprestimo.viewmodel.EmprestimoViewmodel;
import com.messyo.livraria.livro.dto.LivroDTO;
import com.messyo.livraria.livro.interfaces.ILivroService;
import com.messyo.livraria.livro.mapper.LivroMapper;
import com.messyo.livraria.usuario.dto.UsuarioDTO;
import com.messyo.livraria.usuario.interfaces.IUsuarioService;
import com.messyo.livraria.usuario.mapper.UsuarioMapper;
import io.micrometer.core.instrument.util.StringUtils;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        verifyIfUsuarioExists(emprestimoDTO.getUsuarioEmprestimo().getUsuarioId());
        verifyIfLivroExists(emprestimoDTO.getLivroEmprestimo().getLivroId());
        int quantidade = _livroService.verifyIfLivroIsAvailable(emprestimoDTO.getLivroEmprestimo().getLivroId());
        Emprestimo emprestimoToSave = _emprestimoMapper.toModel(emprestimoDTO);
//        emprestimoToSave.setDataDevolucao(emprestimoToSave.getPrevisaoDevolucao());
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
    public void verifyIfUsuarioExists(Long id){
        _usuarioService.findById(id);
    }

    @Override
    public void verifyIfLivroExists(Long id){
        _livroService.findById(id);
    }

    @Override
    public void verifyIfDoesNotExists(Long id) {
        getEmprestimo(id);
    }

    @Override
    public EmprestimoDTO findById(Long id) throws EmprestimoNotFoundException {
        Emprestimo e = getEmprestimo(id);

        return _emprestimoMapper.toDTO(e);
    }

    private Emprestimo getEmprestimo(Long id) {
        return _emprestimoRepository.findById(id).orElseThrow(() -> new EmprestimoNotFoundException(id));
    }

    @Override
    public List<EmprestimoDTO> findByUsuarioId(long usuarioId) {
        UsuarioDTO u = _usuarioMapper.vmToDTO(_usuarioService.findById(usuarioId));
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
        UsuarioDTO u = _usuarioMapper.vmToDTO(_usuarioService.findById(usuarioId));
        LivroDTO l = _livroService.findById(livroId);
        List<Emprestimo> e = _emprestimoRepository.findByLivroEmprestimoAndUsuarioEmprestimo(_livroMapper.toModel(l), _usuarioMapper.toModel(u));
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
    public EmprestimoDTO updateEmprestimo(EmprestimoViewmodel vm) throws EmprestimoNotFoundException {
        EmprestimoDTO e = _emprestimoMapper.toDTO(this.getEmprestimo(vm.getEmprestimoId()));
        e.setLivroEmprestimo(vm.getLivroEmprestimo() == null ? e.getLivroEmprestimo() : vm.getLivroEmprestimo());
        e.setUsuarioEmprestimo(vm.getUsuarioEmprestimo() == null ? e.getUsuarioEmprestimo() : vm.getUsuarioEmprestimo());
        e.setPrevisaoDevolucao(vm.getPrevisaoDevolucao() == null ? e.getPrevisaoDevolucao() : vm.getPrevisaoDevolucao());
        e.setDataDevolucao(vm.getDataDevolucao() == null ? e.getDataDevolucao() : vm.getDataDevolucao());
        e.setStatusEmprestimo(StringUtils.isEmpty(vm.getStatusEmprestimo()) ? e.getStatusEmprestimo() : vm.getStatusEmprestimo());
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
    public Long devolucaoDeLivro(Long emprestimoId) {
        verifyIfDoesNotExists(emprestimoId);
        EmprestimoDTO emprestimo = this.findById(emprestimoId);
//        LocalDateTime dataDevolucao = convertToLocalDateTimeViaSqlTimestamp(emprestimo.getDataDevolucao());
//        LocalDateTime previsaoDevolucao = convertToLocalDateTimeViaSqlTimestamp(emprestimo.getPrevisaoDevolucao());
        LocalDate dataDevolucao = LocalDate.now();
        if (dataDevolucao.isAfter(emprestimo.getPrevisaoDevolucao())) {
            emprestimo.setStatusEmprestimo("Devolvido com atraso");
        } else {
            emprestimo.setStatusEmprestimo("Devolvido");
        }
        Emprestimo emprestimoToUpdate = _emprestimoMapper.toModel(emprestimo);
        emprestimoToUpdate.setDataDevolucao(dataDevolucao);
        _emprestimoRepository.save(emprestimoToUpdate);
        _livroService.incrementarQuantidade(emprestimo.getLivroEmprestimo().getLivroId(), emprestimo.getLivroEmprestimo().getQuantidadeDisponivel());
        return emprestimo.getEmprestimoId();
    }

    @Override
    public Long removeById(Long id) throws EmprestimoNotFoundException {
        EmprestimoDTO e = this.findById(id);
        Emprestimo emprestimoToRemove = _emprestimoMapper.toModel(e);
        _emprestimoRepository.delete(emprestimoToRemove);
        _livroService.incrementarQuantidade(e.getLivroEmprestimo().getLivroId(), e.getLivroEmprestimo().getQuantidadeDisponivel());
        return e.getEmprestimoId();
    }
}
