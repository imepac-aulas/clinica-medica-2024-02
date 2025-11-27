package br.edu.imepac.common.dtos;

import lombok.Data;

@Data
public class PerfilDtos {public class PerfilResponseDTO {

    private Long id;
    private String nome;
    private Set<EnumFuncionalidades> funcionalidades;
}
