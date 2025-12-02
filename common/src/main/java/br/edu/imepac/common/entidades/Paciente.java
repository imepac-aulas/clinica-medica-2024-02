package br.edu.imepac.common.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Entity
@Table(name = "pacientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente extends Pessoa {

    @NotBlank(message = "O número do cartão do SUS é obrigatório.")
    @Column(name = "numero_cartao_sus", length = 20, nullable = false, unique = true)
    private String numeroCartaoSUS;

    @NotBlank(message = "O CPF é obrigatório.")
    @CPF(message = "CPF inválido.")
    @Column(length = 14, nullable = false, unique = true)
    private String cpf;

    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    private List<Consulta> consultas;
}
