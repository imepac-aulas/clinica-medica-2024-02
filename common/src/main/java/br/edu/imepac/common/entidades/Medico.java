package br.edu.imepac.common.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medico extends Pessoa {

    @NotBlank(message = "O CRM é obrigatório.")
    @Column(nullable = false, unique = true)
    private String crm;

    @NotNull(message = "A especialidade é obrigatória.")
    @ManyToOne
    @JoinColumn(name = "id_especialidade")
    private Especialidade especialidade;

    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas;
}
