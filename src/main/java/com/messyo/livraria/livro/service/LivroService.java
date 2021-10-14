package com.messyo.livraria.livro.service;

import com.messyo.livraria.livro.dto.LivroDTO;
import com.messyo.livraria.livro.entity.Livro;
import com.messyo.livraria.livro.mapper.LivroMapper;
import com.messyo.livraria.livro.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {
    private LivroRepository _livroRepository;

    private final LivroMapper _livroMapper = LivroMapper.INSTANCE;

    @Autowired
    public LivroService(LivroRepository livroRepository){
        _livroRepository = livroRepository;
    }

    public String create(LivroDTO livroDTO) {
        Livro livroToSave = _livroMapper.toModel(livroDTO);
        Livro savedLivro = null;
        try {
            savedLivro = _livroRepository.save(livroToSave);
        }catch (Exception ex){
            System.out.println("*************************************");
            System.out.println(ex);
            System.out.println("*************************************");
            return "Erro no sistema!";
        }
        return "Livro criado com id " + savedLivro.getLivroId();
    }

//    public LivroDTO findById(Long id) throws LivroNotFoundException {
//        Livro l = _livroRepository.findById(id).orElseThrow(() -> new LivroNotFoundException(id));
//
//        return _livroMapper.toDTO(l);
//    }
//
//    public List<LivroDTO> getAll() {
//        List<Livro> l = _livroRepository.findAll();
//        List<LivroDTO> lDTO = new ArrayList<>();
//        for (Livro li : l) {
//            lDTO.add(_livroMapper.toDTO(li));
//        }
//        return lDTO;
//    }
//    public LivroDTO updateLivro(LivroViewModel livroVM) throws LivroNotFoundException {
//        LivroDTO l = this.findById(livroVM.getLivroId());
//        l.setAutor(StringUtils.isEmpty(livroVM.getAutor()) ? l.getAutor() : livroVM.getAutor());
//        l.setEditora(livroVM.getEditora()==null ? l.getEditora() : livroVM.getEditora());
//        l.setLancamento(livroVM.getLancamento()==null ? l.getLancamento() : livroVM.getLancamento());
//        l.setNomeLivro(StringUtils.isEmpty(livroVM.getNomeLivro()) ? l.getNomeLivro() : livroVM.getNomeLivro());
//        l.setUpdatedAt(new Date());
//        Livro livroToUpdate = _livroMapper.toModel(l);
//        _livroRepository.save(livroToUpdate);
//        return l;
//    }
//
//    public Long removeById(Long id) throws LivroNotFoundException {
//        LivroDTO l = this.findById(id);
//        Livro livroToRemove = _livroMapper.toModel(l);
//        _livroRepository.delete(livroToRemove);
//        return l.getLivroId();
//    }
//
//    public List<LivroDTO> findLivroByParameter(String parameter, String value) throws LivroNotFoundException {
//        List<Livro> l = null;
//        switch (parameter){
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
//        if(l != null && !l.isEmpty()){
//            List<LivroDTO> lDTO = new ArrayList<>();
//            for (Livro li: l
//                 ) {
//                lDTO.add(_livroMapper.toDTO(li));
//            }
//            return lDTO;
//        }
//        throw new LivroNotFoundException("Nenhum livro encontrado com esses parâmetros: " + parameter + " - " + value);
//    }
}

