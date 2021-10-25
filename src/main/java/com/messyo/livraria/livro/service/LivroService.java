package com.messyo.livraria.livro.service;

import com.messyo.livraria.editora.exception.EditoraNotFoundException;
import com.messyo.livraria.editora.mapper.EditoraMapper;
import com.messyo.livraria.editora.repository.EditoraRepository;
import com.messyo.livraria.emprestimo.exception.EmprestimoLivroNotAvailableException;
import com.messyo.livraria.livro.dto.LivroDTO;
import com.messyo.livraria.livro.entity.Livro;
import com.messyo.livraria.livro.exception.LivroAlreadyExistsException;
import com.messyo.livraria.livro.exception.LivroNotFoundException;
import com.messyo.livraria.livro.interfaces.ILivroService;
import com.messyo.livraria.livro.mapper.LivroMapper;
import com.messyo.livraria.livro.repository.LivroRepository;
import com.messyo.livraria.livro.viewmodel.LivroViewModel;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService implements ILivroService {

    @Autowired
    private LivroRepository _livroRepository;

    @Autowired
    private EditoraRepository _editoraRepository;

    @Autowired
    protected LivroMapper _livroMapper;

    @Autowired
    protected EditoraMapper _editoraMapper;

    @Override
    public LivroDTO create(LivroDTO livroDTO) {
        verifyIfExistsByName(livroDTO.getNomeLivro());
        verifyIfEditoraExists(livroDTO.getEditora().getEditoraId());
        Livro livroToSave = _livroMapper.toModel(livroDTO);
        Livro savedLivro = _livroRepository.save(livroToSave);
        return _livroMapper.toDTO(savedLivro);
    }

    @Override
    public void verifyIfEditoraExists(Long editoraId) {
        _editoraRepository.findById(editoraId)
                .orElseThrow(() -> new EditoraNotFoundException(editoraId));
    }

    @Override
    public void verifyIfExistsByName(String nome) {
        _livroRepository.findByNomeLivro(nome)
                .ifPresent(livro -> {
                    throw new LivroAlreadyExistsException((livro.getNomeLivro()));
                });
    }

    @Override
    public Integer verifyIfLivroIsAvailable(Long id) {
        Optional<Livro> l = _livroRepository.findById(id);
        l.ifPresent(livro -> {
                    if(livro.getQuantidadeDisponivel() == 0){
                        throw new EmprestimoLivroNotAvailableException(livro.getLivroId());
                    }
                });
        return l.get().getQuantidadeDisponivel();
    }

    @Override
    public LivroDTO findById(Long id) throws LivroNotFoundException {
        Livro l = getLivro(id);

        return _livroMapper.toDTO(l);
    }

    private Livro getLivro(Long id) {
        return _livroRepository.findById(id).orElseThrow(() -> new LivroNotFoundException(id));
    }

    @Override
    public List<LivroDTO> getAll() {
        return _livroRepository.findAll().stream().map(_livroMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public LivroDTO updateLivro(LivroViewModel vm) throws LivroNotFoundException {
        LivroDTO l = _livroMapper.toDTO(this.getLivro(vm.getLivroId()));
        l.setAutor(StringUtils.isEmpty(vm.getAutor()) ? l.getAutor() : vm.getAutor());
        l.setEditora(vm.getEditora() == null ? l.getEditora() : _editoraMapper.vmToDTO(vm.getEditora()));
        l.setLancamento(vm.getLancamento() == null ? l.getLancamento() : vm.getLancamento());
        l.setNomeLivro(StringUtils.isEmpty(vm.getNomeLivro()) ? l.getNomeLivro() : vm.getNomeLivro());
        l.setQuantidadeDisponivel(vm.getQuantidadeDisponivel() == null ? l.getQuantidadeDisponivel() : vm.getQuantidadeDisponivel());
        Livro livroToUpdate = _livroMapper.toModel(l);
        Livro savedLivro = _livroRepository.save(livroToUpdate);
        return _livroMapper.toDTO(savedLivro);
    }

    @Override
    public Long removeById(Long id) throws LivroNotFoundException {
        LivroDTO l = this.findById(id);
        Livro livroToRemove = _livroMapper.toModel(l);
        _livroRepository.delete(livroToRemove);
        return l.getLivroId();
    }

    @Override
    public void setLivroQuantidadeById(Long livroId, Integer quantidade){
        LivroDTO l = this.findById(livroId);
        this.verifyIfLivroIsAvailable(livroId);
        l.setQuantidadeDisponivel(quantidade);
        _livroRepository.save(_livroMapper.toModel(l));
    }

    @Override
    public void decrementarQuantidade(Long livroId, Integer quantidade) {
        this.setLivroQuantidadeById(livroId, quantidade - 1);
    }

    @Override
    public void incrementarQuantidade(Long livroId, Integer quantidade) {
        this.setLivroQuantidadeById(livroId, quantidade + 1);
    }

//    public List<LivroDTO> findLivroByParameter(String parameter, String value) throws LivroNotFoundException {
//        List<Livro> l = null;
//        switch (parameter) {
//            case "autor":
//                l = _livroRepository.findByAutor(parameter);
//                break;
//            case "lancamento":
//                l = _livroRepository.findByLancamento(parameter);
//            case "livroId":
//                throw new LivroNotFoundException("Parâmetro não suportado nesta rota. Tente a rota padrão para a consulta por Id");
//            default:
//                throw new LivroNotFoundException("Parâmetro não suportado");
//
//        }
//        System.out.println(l);
//        if (l != null && !l.isEmpty()) {
//            List<LivroDTO> lDTO = new ArrayList<>();
//            for (Livro li : l
//            ) {
//                lDTO.add(_livroMapper.toDTO(li));
//            }
//            return lDTO;
//        }
//        throw new LivroNotFoundException("Nenhum livro encontrado com esses parâmetros: " + parameter + " - " + value);
//    }
}

