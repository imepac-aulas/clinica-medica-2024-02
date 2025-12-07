package br.edu.imepac.atendimento.dtos.paciente;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PacienteCreateRequest {
    private String nome;
    private String telefone;
    private String email;
    private LocalDate dataNascimento;
    private String numeroCartaoSUS;
    private String cpf;
}