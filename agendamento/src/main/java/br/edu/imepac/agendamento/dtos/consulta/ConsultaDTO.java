package br.edu.imepac.agendamento.dtos.consulta;

import br.edu.imepac.common.utils.StatusConsultaEnum;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ConsultaDTO {
    private Long id;
    private Long pacienteId;
    private Long medicoId;
    private Long convenioId;
    private String carteiraConvenio;
    private LocalDateTime dataHora;
    private StatusConsultaEnum status;
    private Long prontuarioId;
    private Long consultaOriginalId;
}
