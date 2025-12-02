package br.edu.imepac.administrativo.dtos.secretaria;

import lombok.Data;
import java.time.LocalDate;

@Data
public class SecretariaDTO {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private LocalDate dataNascimento;
    private String pis;
    private String sexo;
    private String rg;
    private Long usuarioId;
}
