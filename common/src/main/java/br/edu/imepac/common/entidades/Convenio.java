package br.edu.imepac.common.entidades;

import br.edu.imepac.common.utils.StatusConvenioEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name = "convenios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Convenio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do convênio é obrigatório.")
    @Column(nullable = false, unique = true)
    private String nome;

    @NotBlank(message = "O CNPJ é obrigatório.")
    @Column(nullable = false, unique = true)
    private String cnpj;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "Email inválido.")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "O telefone é obrigatório.")
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusConvenioEnum status;
}
