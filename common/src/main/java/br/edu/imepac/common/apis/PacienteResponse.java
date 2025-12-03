package br.edu.imepac.common.apis;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PacienteResponse {

    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private LocalDate dataNascimento;
    private String cpf;
    private String numeroCartaoSUS;
}