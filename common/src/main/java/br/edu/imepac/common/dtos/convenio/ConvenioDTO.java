package br.edu.imepac.common.dtos.convenio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ConvenioDTO {

    private Long id;
    private String nome;
    private String codigo;
}
