package br.edu.imepac.common.dtos.medico;
import br.edu.imepac.common.dtos.pessoa.PessoaRequestDto;
import lombok.Data;
@Data

public class MedicoRequestDto extends PessoaRequestDto {
    private int crm;

}
