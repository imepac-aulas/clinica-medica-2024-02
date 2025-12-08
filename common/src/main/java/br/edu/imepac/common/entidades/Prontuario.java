package br.edu.imepac.common.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prontuarios")
@EntityListeners(AuditingEntityListener.class) // Habilita o gerenciamento automático de datas
public class Prontuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A queixa principal é obrigatória.")
    @Column(name = "queixa_principal", nullable = false, columnDefinition = "TEXT")
    private String queixaPrincipal;

    @NotBlank(message = "O diagnóstico é obrigatório.")
    private String diagnostico;

    @Column(name = "historico_medico",
            columnDefinition = "TEXT")
    private String historicoMedico;

    private String alergias;

    @Column(name = "medicamentos_em_uso", columnDefinition = "TEXT")
    private String medicamentosEmUso;

    @Column(name = "exames_solicitados", columnDefinition = "TEXT")
    private String examesSolicitados;

    @Column(name = "resultados_exames", columnDefinition = "TEXT")
    private String resultadosExames;

    @CreatedDate
    @Column(name = "data_abertura", nullable = false, updatable = false)
    private LocalDateTime dataAbertura;

    @LastModifiedDate
    @Column(name = "data_ultima_atualizacao")
    private LocalDateTime dataUltimaAtualizacao;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @OneToOne(mappedBy = "prontuario")
    private Consulta consulta;
}
