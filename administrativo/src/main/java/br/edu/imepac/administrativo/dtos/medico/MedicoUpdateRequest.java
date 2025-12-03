package br.edu.imepac.administrativo.dtos.medico;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MedicoUpdateRequest {
    private String nome;
    private String telefone;
    private String email;
    private LocalDate dataNascimento;
    private String crm;
    private Long especialidadeId;
}
