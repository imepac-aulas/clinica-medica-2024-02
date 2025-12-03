package br.edu.imepac.administrativo.dtos.especialidade;

import lombok.Data;

@Data
public class EspecialidadeCreateRequest {
    private String nome;
    private String descricao;
}
