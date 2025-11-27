package br.edu.imepac.common.entidades;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "prontuarios")
public class Prontuario {
    @id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String queixaPrincipal;
    private String diagnostico;

    @Column(columnDefinition = "H")
    private String historicoMedico;

    private String alergias;
    private String medicamentosEmUso;

    @Column(name = "exames_definition")
    private String examesSolicitados;

    @Column(name = "TEXT")
    private String resultadosExames;

    private LocalDateTime dataAbertura = LocalDateTime.now();
    private LocalDateTime dataUltimaAtualizacao;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @OneToOne(mappedBy = "prontuario")
    private Consulta consultas;
}

