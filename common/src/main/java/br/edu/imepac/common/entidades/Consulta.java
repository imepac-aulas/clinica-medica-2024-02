package br.edu.imepac.common.entidades;

import br.edu.imepac.common.utils.StatusConsultaEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O paciente é obrigatório.")
    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @NotNull(message = "O médico é obrigatório.")
    @ManyToOne(optional = false)
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "convenio_id")
    private Convenio convenio;

    @Column(name = "carteira_convenio", length = 30)
    private String carteiraConvenio;

    @NotNull(message = "A data e hora da consulta são obrigatórias.")
    @Future(message = "A data e hora da consulta devem ser no futuro.")
    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @NotNull(message = "O status da consulta é obrigatório.")
    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private StatusConsultaEnum status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prontuario_id", referencedColumnName = "id")
    private Prontuario prontuario;

    @ManyToOne
    @JoinColumn(name = "consulta_original_id")
    private Consulta consultaOriginal;
}
