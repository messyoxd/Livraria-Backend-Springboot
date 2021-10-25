//package com.messyo.livraria.emprestimo.controller;
//
//import com.messyo.livraria.emprestimo.builder.EmprestimoDTOBuilder;
//import com.messyo.livraria.emprestimo.service.EmprestimoService;
//import com.messyo.livraria.usuario.builder.UsuarioDTOBuilder;
//import com.messyo.livraria.usuario.controller.UsuarioController;
//import com.messyo.livraria.usuario.service.UsuarioService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
//
//@ExtendWith(MockitoExtension.class)
//public class EmprestimoControllerTest {
//    @Mock
//    private EmprestimoService emprestimoService;
//
//    @InjectMocks
//    private EmprestimoController emprestimoController;
//
//    private MockMvc mockMvc;
//
//    private EmprestimoDTOBuilder emprestimoDTOBuilder;
//
//    @BeforeEach
//    void setUp() {
//        emprestimoDTOBuilder = EmprestimoDTOBuilder.builder().build();
//        mockMvc = MockMvcBuilders.standaloneSetup(emprestimoDTOBuilder)
//                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
//                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
//                .build();
//    }
//}
