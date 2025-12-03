package br.edu.imepac.agendamento.controllers.paciente;

import br.edu.imepac.agendamento.services.paciente.PacienteService;
import br.edu.imepac.common.apis.PacienteApi;
import br.edu.imepac.common.apis.PacienteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PacienteApiController implements PacienteApi {

    private final PacienteService pacienteService;

    @Override
    public PacienteResponse buscarPorId(Long id) {
        return pacienteService.buscarParaIntegracao(id);
    }
}