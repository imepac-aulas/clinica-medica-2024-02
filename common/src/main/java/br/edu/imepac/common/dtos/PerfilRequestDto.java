package br.edu.imepac.common.dtos;

import lombok.Data;

@Data
public class PerfilRequestDto {
    private String nome;
    private Set<EnumFuncionalidades> funcionalidades;
}
