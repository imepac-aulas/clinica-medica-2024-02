package br.edu.imepac.common.entidades;

import br.edu.imepac.common.entidades.Pessoa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "secretarias")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Secretaria extends Pessoa {

    @Column(nullable = false, unique = true, length = 20)
    private String pis;

    @Column(length = 20)
    private String sexo;

    @Column(length = 20)
    private String rg;

    @OneToOne(mappedBy = "secretaria")
    private Usuario usuario;
}