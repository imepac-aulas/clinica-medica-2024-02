package br.edu.imepac.common.dtos.convenio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ConvenioRequestDTO {

    private String nome;
    private String codigo;
}
