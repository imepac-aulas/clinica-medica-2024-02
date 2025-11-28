package br.edu.imepac.common.entidades;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medico extends Pessoa {

    private String crm;

    @ManyToOne
    @JoinColumn(name = "id_especialidade")
    private Especialidade especialidade;

    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas;
}
