package br.edu.imepac.common.dtos.secretaria;

import br.edu.imepac.common.dtos.pessoa.PessoaRequestDto;
import lombok.Data;

@Data
public class SecretariaRequestDto extends PessoaRequestDto {
    private String sexo;
    private String rg;
}
