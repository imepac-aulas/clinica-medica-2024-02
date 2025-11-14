package br.edu.imepac.common.dtos.paciente;

import br.edu.imepac.common.dtos.pessoa.PessoaRequestDto;
import lombok.Data;

@Data

public class PacienteRequestDto extends PessoaRequestDto {
    private String cpf;
    private String dataDeNascimento;
}
