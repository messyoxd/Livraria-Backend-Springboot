package com.messyo.livraria.usuario.service;

import com.messyo.livraria.usuario.dto.AuthenticatedUser;
import com.messyo.livraria.usuario.entity.Usuario;
import com.messyo.livraria.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository _usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u = _usuarioRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException(String.format("User not found with email %s", username)));
        return new AuthenticatedUser(
                u.getEmail(),
                u.getPassword(),
                u.getRole().getDescription()
        );
    }
}
