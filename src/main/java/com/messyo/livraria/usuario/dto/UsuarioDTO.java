package com.messyo.livraria.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long usuarioId;

    @NotBlank(message = "O campo Nome Completo é obrigatório")
    @Size(max = 50)
    private String nomeCompleto;

    @NotBlank(message = "O campo Enderoço é obrigatório")
    @Size(max = 100)
    private String endereco;

    @NotBlank(message = "O campo Cidade é obrigatório")
    @Size(max = 100)
    private String cidade;
}
