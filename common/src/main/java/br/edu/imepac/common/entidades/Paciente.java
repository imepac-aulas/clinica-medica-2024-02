package br.edu.imepac.common.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "pacientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paciente extends Pessoa {

    @Column(name = "numero_cartao_sus", length = 20)
    private String numeroCartaoSUS;

    @Column(length = 14, nullable = false, unique = true)
    private String cpf;

    @Column(length = 255)
    private String endereco;

    // -------------------------
    // RELACIONAMENTOS
    // -------------------------

    // Paciente --> Consulta (um paciente pode ter várias consultas)
    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    private List<Consulta> consultas;
}