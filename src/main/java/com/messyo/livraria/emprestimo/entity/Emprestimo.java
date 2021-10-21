package com.messyo.livraria.emprestimo.entity;

import com.messyo.livraria.entity.Auditable;
import com.messyo.livraria.livro.entity.Livro;
import com.messyo.livraria.usuario.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(
        name = "emprestimos"
)
@Data
public class Emprestimo extends Auditable {
    @Id
    @SequenceGenerator(name = "emprestimo_sequence", sequenceName = "emprestimo_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emprestimo_sequence")
    private Long emprestimoId;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "usuario_id",
            referencedColumnName = "usuarioId"
    )
    private Usuario usuarioEmprestimo;

    @JoinColumn(
            name = "livro_id",
            referencedColumnName = "livroId"
    )
    @ManyToOne(optional = false)
    private Livro livroEmprestimo;

    @Column(name = "previsao_devolucao", nullable = false)
    private LocalDate previsaoDevolucao;

    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;

    @Column(name = "status_emprestimo", nullable = false, columnDefinition = "TEXT")
    private String statusEmprestimo;

}
