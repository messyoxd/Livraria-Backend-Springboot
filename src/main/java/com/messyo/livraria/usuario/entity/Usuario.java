package com.messyo.livraria.usuario.entity;

import com.messyo.livraria.entity.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends Auditable {
    @Id
    @SequenceGenerator(name = "usuario_sequence", sequenceName = "usuario_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_sequence")
    private Long usuarioId;

    @Column(name = "nome_completo", nullable = false, columnDefinition = "TEXT")
    private String nomeCompleto;

    @Column(name = "endereco", nullable = false, columnDefinition = "TEXT")
    private String endereco;

    @Column(name = "cidade", nullable = false, columnDefinition = "TEXT")
    private String cidade;

}
