package br.edu.imepac.common.dtos.paciente;

import br.edu.imepac.common.dtos.pessoa.PessoaDto;
import lombok.Data;

@Data

public class PacienteDto extends PessoaDto {
    private String cpf;
    private String dataDeNascimento;
}
