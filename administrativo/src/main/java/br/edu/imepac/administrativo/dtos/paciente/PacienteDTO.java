package br.edu.imepac.administrativo.dtos.paciente;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PacienteDTO {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private LocalDate dataNascimento;
    private String numeroCartaoSUS;
    private String cpf;
}
