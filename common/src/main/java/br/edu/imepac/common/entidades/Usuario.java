package br.edu.imepac.common.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String senha;

    public enum EnumStatusUsuario {
        ATIVO,
        INATIVO
    }
}
