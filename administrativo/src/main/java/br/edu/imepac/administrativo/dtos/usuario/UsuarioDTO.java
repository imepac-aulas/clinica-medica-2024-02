package br.edu.imepac.administrativo.dtos.usuario;

import br.edu.imepac.common.entidades.Usuario;
import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String nome;
    private Usuario.EnumStatusUsuario status;
}
