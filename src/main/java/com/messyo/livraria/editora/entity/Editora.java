package com.messyo.livraria.editora.entity;

import com.messyo.livraria.entity.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(
        name = "editoras"
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Editora extends Auditable {
    @Id
    @SequenceGenerator(name = "editora_sequence", sequenceName = "editora_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "editora_sequence")
    private Long editoraId;

    @Column(name = "nome", nullable = false, columnDefinition = "TEXT")
    private String nome;

    @Column(name = "cidade", nullable = false, columnDefinition = "TEXT")
    private String cidade;

}
