package br.edu.imepac.common.dtos.usuario;

import lombok.Data;

@Data
public class UsuarioRequestDto {
    private String nome;
    private String senha;
    public String status;
}
