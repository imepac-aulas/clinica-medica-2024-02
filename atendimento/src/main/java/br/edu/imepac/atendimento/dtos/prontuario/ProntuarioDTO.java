package br.edu.imepac.atendimento.dtos.prontuario;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProntuarioDTO {
    private Long id;
    private String queixaPrincipal;
    private String diagnostico;
    private String historicoMedico;
    private String alergias;
    private String medicamentosEmUso;
    private String examesSolicitados;
    private String resultadosExames;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataUltimaAtualizacao;
    private String observacoes;
    private ConsultaDTO consulta;

    @Data
    public static class ConsultaDTO {
        private Long id;
        private LocalDateTime dataHora;
        private Long pacienteId;
        private Long medicoId;
    }
}
