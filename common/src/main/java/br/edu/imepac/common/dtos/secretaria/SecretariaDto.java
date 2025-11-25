package br.edu.imepac.common.dtos.secretaria;

import br.edu.imepac.common.dtos.pessoa.PessoaDto;
import lombok.Data;

@Data
public class SecretariaDto extends PessoaDto {
    private String sexo;
    private String rg;
}
