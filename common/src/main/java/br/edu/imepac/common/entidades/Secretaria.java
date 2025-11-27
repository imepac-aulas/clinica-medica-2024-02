package br.edu.imepac.common.entidades;

import br.edu.imepac.common.entidades.Pessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "secretarias")
public class Secretaria extends Pessoa {

    private String pis;

    private String sexo;

    private String rg;
}