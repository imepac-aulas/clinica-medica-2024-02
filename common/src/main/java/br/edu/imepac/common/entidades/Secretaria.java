package br.edu.imepac.common.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "secretarias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Secretaria extends Pessoa {

    @NotBlank(message = "O PIS é obrigatório.")
    @Column(nullable = false, unique = true, length = 20)
    private String pis;

    @NotBlank(message = "O sexo é obrigatório.")
    @Column(length = 20)
    private String sexo;

    @NotBlank(message = "O RG é obrigatório.")
    @Column(length = 20)
    private String rg;

    @NotNull(message = "O usuário é obrigatório.")
    @OneToOne(mappedBy = "secretaria")
    private Usuario usuario;
}
