package com.messyo.livraria.livro.entity;

import com.messyo.livraria.editora.entity.Editora;
import com.messyo.livraria.entity.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "livros"
)
@Data
public class Livro extends Auditable {
    @Id
    @SequenceGenerator(name = "livro_sequence", sequenceName = "livro_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "livro_sequence")
    private Long livroId;

    @ManyToOne(
            optional = false,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "editora_id",
            referencedColumnName = "editoraId"
    )
    private Editora editora;

    @Column(name = "autor", nullable = false, columnDefinition = "TEXT")
    private String autor;

    @Column(name = "nome_livro", nullable = false, columnDefinition = "TEXT")
    private String nomeLivro;

    @Column(name = "lancamento", nullable = false)
    private LocalDateTime lancamento;

    @Column(name = "quantidade_disponivel", nullable = false)
    private Integer quantidadeDisponivel;

}

