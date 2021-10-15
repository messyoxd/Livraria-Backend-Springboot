package com.messyo.livraria.emprestimo.service;

import com.messyo.livraria.emprestimo.builder.EmprestimoDTOBuilder;
import com.messyo.livraria.emprestimo.mapper.EmprestimoMapper;
import com.messyo.livraria.emprestimo.repository.EmprestimoRepository;
import com.messyo.livraria.usuario.builder.UsuarioDTOBuilder;
import com.messyo.livraria.usuario.mapper.UsuarioMapper;
import com.messyo.livraria.usuario.repository.UsuarioRepository;
import com.messyo.livraria.usuario.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
    private final EmprestimoMapper emprestimoMapper = EmprestimoMapper.INSTANCE;

    @Mock
    private EmprestimoRepository emprestimoRepository;

    @InjectMocks
    private EmprestimoService emprestimoService;

    private EmprestimoDTOBuilder emprestimoDTOBuilder;

    @BeforeEach
    void setUp() {
        emprestimoDTOBuilder = EmprestimoDTOBuilder.builder().build();
    }
}
