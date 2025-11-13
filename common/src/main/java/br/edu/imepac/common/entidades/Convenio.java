package br.edu.imepac.common.entidades;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "convenio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Convenio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(unique = true, length = 50)
    private String codigo;

    @Column(length = 255)
    private String descricao;
}