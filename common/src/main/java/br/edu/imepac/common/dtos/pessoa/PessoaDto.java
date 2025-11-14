package br.edu.imepac.common.dtos.pessoa;

import lombok.Data;

@Data
public class PessoaDto {
    private long id;
    private String nome;
    private String email;
}
