package br.edu.imepac.common.dtos.medico;
import br.edu.imepac.common.dtos.pessoa.PessoaDto;
import lombok.Data;
@Data

public class MedicoDto extends PessoaDto {
    private int crm;

}