package com.messyo.livraria.livro.service;

import com.messyo.livraria.livro.dto.LivroDTO;
import com.messyo.livraria.livro.entity.Livro;
import com.messyo.livraria.livro.exception.LivroAlreadyExistsException;
import com.messyo.livraria.livro.exception.LivroNotFoundException;
import com.messyo.livraria.livro.mapper.LivroMapper;
import com.messyo.livraria.livro.repository.LivroRepository;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {
    private LivroRepository _livroRepository;

    private final LivroMapper _livroMapper = LivroMapper.INSTANCE;

    @Autowired
    public LivroService(LivroRepository livroRepository) {
        _livroRepository = livroRepository;
    }

    public LivroDTO create(LivroDTO livroDTO) {
        verifyIfExists(livroDTO.getLivroId());
        Livro livroToSave = _livroMapper.toModel(livroDTO);
        Livro savedLivro = _livroRepository.save(livroToSave);
        return _livroMapper.toDTO(savedLivro);
    }

    private void verifyIfExists(Long id) {
        _livroRepository.findById(id)
                .ifPresent(livro -> {
                    throw new LivroAlreadyExistsException((livro.getLivroId()));
                });
    }

    public LivroDTO findById(Long id) throws LivroNotFoundException {
        Livro l = _livroRepository.findById(id).orElseThrow(() -> new LivroNotFoundException(id));

        return _livroMapper.toDTO(l);
    }

    public List<LivroDTO> getAll() {
        return _livroRepository.findAll().stream().map(_livroMapper::toDTO).collect(Collectors.toList());
    }

    public LivroDTO updateLivro(LivroDTO livroDTO) throws LivroNotFoundException {
        LivroDTO l = this.findById(livroDTO.getLivroId());
        l.setAutor(StringUtils.isEmpty(livroDTO.getAutor()) ? l.getAutor() : livroDTO.getAutor());
        l.setEditora(livroDTO.getEditora() == null ? l.getEditora() : livroDTO.getEditora());
        l.setLancamento(livroDTO.getLancamento() == null ? l.getLancamento() : livroDTO.getLancamento());
        l.setNomeLivro(StringUtils.isEmpty(livroDTO.getNomeLivro()) ? l.getNomeLivro() : livroDTO.getNomeLivro());
        Livro livroToUpdate = _livroMapper.toModel(l);
        _livroRepository.save(livroToUpdate);
        return l;
    }

    public Long removeById(Long id) throws LivroNotFoundException {
        LivroDTO l = this.findById(id);
        Livro livroToRemove = _livroMapper.toModel(l);
        _livroRepository.delete(livroToRemove);
        return l.getLivroId();
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

