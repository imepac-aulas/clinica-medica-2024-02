package br.edu.imepac.atendimento.dtos.prontuario;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class ProntuarioCreateRequest {
    @NotBlank
    private String queixaPrincipal;
    private String diagnostico;
    private String historicoMedico;
    private String alergias;
    private String medicamentosEmUso;
    private String examesSolicitados;
    private String resultadosExames;
    private String observacoes;
    private Long consultaId;
}
