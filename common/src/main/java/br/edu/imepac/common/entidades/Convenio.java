package br.edu.imepac.common.entidades;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "convenios")
@Data
@NoArgsConstructor
public class Convenio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String empresaConvenio;   // Empresa_Convenio
    private String cnpj;              // CNPJ
    private String telefone;          // Telefone

}