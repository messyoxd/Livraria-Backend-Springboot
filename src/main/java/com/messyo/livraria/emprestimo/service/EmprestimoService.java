package com.messyo.livraria.emprestimo.service;

import com.messyo.livraria.emprestimo.dto.EmprestimoDTO;
import com.messyo.livraria.emprestimo.entity.Emprestimo;
import com.messyo.livraria.emprestimo.mapper.EmprestimoMapper;
import com.messyo.livraria.emprestimo.repository.EmprestimoRepository;
import com.messyo.livraria.livro.repository.LivroRepository;
import com.messyo.livraria.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmprestimoService {
    private EmprestimoRepository _emprestimoRepository;
    private UsuarioRepository _usuarioRepository;
    private LivroRepository _livroRepository;

    private final EmprestimoMapper _emprestimoMapper = EmprestimoMapper.INSTANCE;

    @Autowired
    public EmprestimoService(EmprestimoRepository emprestimoRepository, UsuarioRepository usuarioRepository, LivroRepository livroRepository){
        _livroRepository = livroRepository;
        _usuarioRepository = usuarioRepository;
        _emprestimoRepository = emprestimoRepository;
    }

    public String create(EmprestimoDTO emprestimoDTO) {
        Emprestimo emprestimoToSave = _emprestimoMapper.toModel(emprestimoDTO);
        Emprestimo savedEmprestimo = null;
        try {
            savedEmprestimo = _emprestimoRepository.save(emprestimoToSave);
        }catch (Exception ex){
            System.out.println("*************************************");
            System.out.println(ex);
            System.out.println("*************************************");
            return "Erro no sistema!";
        }
        return "Livro criado com id " + savedEmprestimo.getEmprestimoId();
    }

//    public EmprestimoDTO findById(Long id) throws EmprestimoNotFoundException {
//        Emprestimo e = _emprestimoRepository.findById(id).orElseThrow(() -> new EmprestimoNotFoundException(id));
//
//        return _emprestimoMapper.toDTO(e);
//    }
//
//    public List<EmprestimoDTO> findByUsuarioId(long usuarioId){
//        Usuario u = _usuarioRepository.getById(usuarioId);
//        List<Emprestimo> e = _emprestimoRepository.findByUsuarioEmprestimo(u);
//        List<EmprestimoDTO> eDTO = new ArrayList<>();
//        for (Emprestimo em : e) {
//            eDTO.add(_emprestimoMapper.toDTO(em));
//        }
//        return eDTO;
//    }
//
//    public List<EmprestimoDTO> findByLivroId(long livroId){
//        Livro l = _livroRepository.getById(livroId);
//        List<Emprestimo> e = _emprestimoRepository.findByLivroEmprestimo(l);
//        List<EmprestimoDTO> eDTO = new ArrayList<>();
//        for (Emprestimo em : e) {
//            eDTO.add(_emprestimoMapper.toDTO(em));
//        }
//        return eDTO;
//    }
//
//    public List<EmprestimoDTO> findByUsuarioIdAndLivroId(long usuarioId, long livroId){
//        Usuario u = _usuarioRepository.getById(usuarioId);
//        Livro l = _livroRepository.getById(livroId);
//        List<Emprestimo> e = _emprestimoRepository.findByLivroEmprestimoAndUsuarioEmprestimo(u,l);
//        List<EmprestimoDTO> eDTO = new ArrayList<>();
//        for (Emprestimo em : e) {
//            eDTO.add(_emprestimoMapper.toDTO(em));
//        }
//        return eDTO;
//    }
//
//    public List<EmprestimoDTO> getAll() {
//        List<Emprestimo> e = _emprestimoRepository.findAll();
//        List<EmprestimoDTO> eDTO = new ArrayList<>();
//        for (Emprestimo em : e) {
//            eDTO.add(_emprestimoMapper.toDTO(em));
//        }
//        return eDTO;
//    }
//
//    public EmprestimoDTO updateEmprestimo(EmprestimoViewModel emprestimoVM) throws EmprestimoNotFoundException {
//        EmprestimoDTO e = this.findById(emprestimoVM.getEmprestimoId());
//        e.setLivroEmprestimo(emprestimoVM.getLivroEmprestimo() == null ? e.getLivroEmprestimo() : emprestimoVM.getLivroEmprestimo());
//        e.setUsuarioEmprestimo(emprestimoVM.getUsuarioEmprestimo() == null ? e.getUsuarioEmprestimo() : emprestimoVM.getUsuarioEmprestimo());
//        e.setPrevisaoDevolucao(emprestimoVM.getPrevisaoDevolucao() == null ? e.getPrevisaoDevolucao() : emprestimoVM.getPrevisaoDevolucao());
//        e.setDataDevolucao(emprestimoVM.getDataDevolucao() == null ? e.getDataDevolucao() : emprestimoVM.getDataDevolucao());
//        e.setStatusEmprestimo(StringUtils.isEmpty(emprestimoVM.getStatusEmprestimo()) ? e.getStatusEmprestimo() : emprestimoVM.getStatusEmprestimo());
//        e.setUpdatedAt(new Date());
//        Emprestimo emprestimoToUpdate = _emprestimoMapper.toModel(e);
//        _emprestimoRepository.save(emprestimoToUpdate);
//        return e;
//    }
//
//    public Long removeById(Long id) throws EmprestimoNotFoundException {
//        EmprestimoDTO e = this.findById(id);
//        Emprestimo emprestimoToRemove = _emprestimoMapper.toModel(e);
//        _emprestimoRepository.delete(emprestimoToRemove);
//        return e.getEmprestimoId();
//    }
}
