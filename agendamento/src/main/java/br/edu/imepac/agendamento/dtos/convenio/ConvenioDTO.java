package br.edu.imepac.agendamento.dtos.convenio;

import lombok.Data;

@Data
public class ConvenioDTO {
    private Long id;
    private String nome;
    private String cnpj;
    private String email;
    private String telefone;
}
