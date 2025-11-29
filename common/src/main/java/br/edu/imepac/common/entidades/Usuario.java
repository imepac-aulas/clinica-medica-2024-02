package br.edu.imepac.common.entidades;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 60)
    private String senha;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private EnumStatusUsuario status = EnumStatusUsuario.ATIVO;
    public enum EnumStatusUsuario {
        ATIVO,
        INATIVO
    }

    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

    @OneToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @OneToOne
    @JoinColumn(name = "secretaria_id")
    private Secretaria secretaria;


}

