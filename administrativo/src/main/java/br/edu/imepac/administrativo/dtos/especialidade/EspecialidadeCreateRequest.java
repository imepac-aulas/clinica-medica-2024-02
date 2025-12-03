package br.edu.imepac.administrativo.dtos.especialidade;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EspecialidadeCreateRequest {
    @NotBlank(message = "O nome da especialidade é obrigatório.")
    private String nome;
    private String descricao;
}
