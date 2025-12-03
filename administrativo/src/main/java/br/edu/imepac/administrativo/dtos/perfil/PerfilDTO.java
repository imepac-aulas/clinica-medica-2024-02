package br.edu.imepac.administrativo.dtos.perfil;

import br.edu.imepac.common.utils.EnumFuncionalidades;
import lombok.Data;
import java.util.Set;

@Data
public class PerfilDTO {
    private Long id;
    private String nome;
    private Set<EnumFuncionalidades> funcionalidades;
}
