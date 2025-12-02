package br.edu.imepac.common.entidades;

import br.edu.imepac.common.utils.EnumFuncionalidades;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "perfil")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do perfil é obrigatório.")
    @Column(nullable = false, unique = true)
    private String nome;

    @NotEmpty(message = "O perfil deve ter pelo menos uma funcionalidade.")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "perfil_funcionalidades",
            joinColumns = @JoinColumn(name = "perfil_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "funcionalidade", nullable = false)
    private Set<EnumFuncionalidades> funcionalidades;
}
