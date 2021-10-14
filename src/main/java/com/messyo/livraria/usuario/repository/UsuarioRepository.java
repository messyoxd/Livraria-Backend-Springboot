package com.messyo.livraria.usuario.repository;

import com.messyo.livraria.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.appUser.appUserRole = 'USER'")
    List<Usuario> findAllClients();

    /*
    List<Usuario> findByFirstName(String firstName);

    List<Usuario> findByFirstNameContaining(String name);

    List<Usuario> findByLastNameNotNull();

    List<Usuario> findByGuardianName(String guardianName);

    Usuario findByFirstNameAndLastName(String firstName, String lastName);

     */
}
