package br.edu.imepac.common.entidades;


import lombok.Data;

@Data
public class Pessoa {
    private Long id;
    private String nome;
    private String email;
}
