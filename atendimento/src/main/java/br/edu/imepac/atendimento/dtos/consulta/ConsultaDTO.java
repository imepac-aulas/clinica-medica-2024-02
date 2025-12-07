package br.edu.imepac.atendimento.dtos.consulta;

import br.edu.imepac.common.entidades.*;
import br.edu.imepac.common.utils.StatusConsultaEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConsultaDTO {
    private Long id;

    private Paciente paciente;

    private Medico medico;

    private Convenio convenio;

    private String carteiraConvenio;

    private LocalDateTime dataHora;

    private StatusConsultaEnum status;

    private Prontuario prontuario;

    private Consulta consultaOriginal;
}
