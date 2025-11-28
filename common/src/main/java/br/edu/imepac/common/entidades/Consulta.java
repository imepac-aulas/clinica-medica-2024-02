package br.edu.imepac.common.entidades;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "convenio_id")
    private Convenio convenio;

    @Column(name = "carteira_convenio", length = 30)
    private String carteiraConvenio;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false, length = 30)
    private String status;

    @OneToOne(mappedBy = "consulta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Prontuario prontuario;
}
