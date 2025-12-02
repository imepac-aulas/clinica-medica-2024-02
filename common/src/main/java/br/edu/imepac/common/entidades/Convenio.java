package br.edu.imepac.common.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name = "convenios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Convenio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do convênio é obrigatório.")
    @Column(nullable = false, unique = true)
    private String nome;

    @NotBlank(message = "O CNPJ é obrigatório.")
    @CNPJ(message = "CNPJ inválido.")
    @Column(nullable = false, unique = true)
    private String cnpj;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "Email inválido.")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "O telefone é obrigatório.")
    private String telefone;
}
