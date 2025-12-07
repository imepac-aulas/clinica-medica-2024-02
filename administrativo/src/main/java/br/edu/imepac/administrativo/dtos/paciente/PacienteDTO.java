package br.edu.imepac.administrativo.dtos.paciente;

import br.edu.imepac.common.entidades.Paciente;
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

    public static PacienteDTO fromEntity(Paciente p) {
        if (p == null) return null;

        PacienteDTO dto = new PacienteDTO();
        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setTelefone(p.getTelefone());
        dto.setEmail(p.getEmail());
        dto.setDataNascimento(p.getDataNascimento());
        dto.setNumeroCartaoSUS(p.getNumeroCartaoSUS());
        dto.setCpf(p.getCpf());
        return dto;
    }
}