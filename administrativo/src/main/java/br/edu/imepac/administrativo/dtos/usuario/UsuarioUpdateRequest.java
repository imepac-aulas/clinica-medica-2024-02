package br.edu.imepac.administrativo.dtos.usuario;

import br.edu.imepac.common.entidades.Usuario;
import lombok.Data;

@Data
public class UsuarioUpdateRequest {
    private String nome;
    private String senha;
    private Usuario.EnumStatusUsuario status;
}
