package br.edu.imepac.administrativo.dtos.perfil;

import br.edu.imepac.common.utils.EnumFuncionalidades;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.Set;

@Data
public class PerfilCreateRequest {
    @NotBlank(message = "O nome do perfil é obrigatório.")
    private String nome;

    @NotEmpty(message = "O perfil deve ter pelo menos uma funcionalidade.")
    private Set<EnumFuncionalidades> funcionalidades;
}
