package br.edu.imepac.administrativo.dtos.especialidade;

import br.edu.imepac.administrativo.dtos.medico.MedicoDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class EspecialidadeDTO {

    @NotNull(message = "O ID da especialidade é obrigatório.")
    private Long id;

    @NotBlank(message = "O nome da especialidade é obrigatório.")
    private String nome;

    private String descricao;

    private List<MedicoDTO> medicos;
}
