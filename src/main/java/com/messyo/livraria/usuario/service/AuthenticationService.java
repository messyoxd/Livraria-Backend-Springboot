package com.messyo.livraria.usuario.service;

import com.messyo.livraria.livro.exception.UsuarioAlreadyExistsException;
import com.messyo.livraria.usuario.dto.*;
import com.messyo.livraria.usuario.entity.Usuario;
import com.messyo.livraria.usuario.mapper.UsuarioMapper;
import com.messyo.livraria.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository _usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenManager jwtTokenManager;

    @Autowired
    protected UsuarioMapper _usuarioMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public MessageDTO signUp(UsuarioDTO usuarioDTO) {
        verifyIfExistsByEmail(usuarioDTO.getEmail());
        Usuario usuarioToSave = _usuarioMapper.toModel(usuarioDTO);

        usuarioToSave.setPassword(passwordEncoder.encode(usuarioToSave.getPassword()));

        Usuario savedUsuario = _usuarioRepository.save(usuarioToSave);

        return MessageDTO.builder()
                .message(String.format("User %s with ID %d was successfully created", savedUsuario.getNomeCompleto(), savedUsuario.getUsuarioId()))
                .build();
    }

    private void verifyIfExistsByEmail(String email) {
        _usuarioRepository.findByEmail(email)
                .ifPresent(usuario -> {
                    throw new UsuarioAlreadyExistsException(email);
                });
    }

    //    @Override
    public JwtResponse createAuthenticationToken(JwtRequest jwtRequest) {
        authenticate(
                jwtRequest.getEmail(),
                jwtRequest.getPassword()
        );

        UserDetails u = this.loadUserByUsername(jwtRequest.getEmail());
        String token = jwtTokenManager.generateToken(u);

        return JwtResponse.builder().jwtToken(token).build();
    }

    private void authenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
        authenticationManager.authenticate(authToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u = _usuarioRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with email %s", username)));
        return new AuthenticatedUser(
                u.getPassword(),
                u.getEmail(),
                u.getRole().getDescription()
        );
    }
}
