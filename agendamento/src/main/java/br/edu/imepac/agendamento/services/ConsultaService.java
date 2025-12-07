package br.edu.imepac.agendamento.services;

import br.edu.imepac.agendamento.dtos.consulta.ConsultaCreateRequest;
import br.edu.imepac.agendamento.dtos.consulta.ConsultaDTO;
import br.edu.imepac.agendamento.dtos.consulta.ConsultaUpdateRequest;
import br.edu.imepac.common.exceptions.ResourceNotFoundException;
import br.edu.imepac.agendamento.repository.ConsultaRepository;
import br.edu.imepac.common.entidades.Consulta;
import br.edu.imepac.common.entidades.Convenio;
import br.edu.imepac.common.entidades.Medico;
import br.edu.imepac.common.entidades.Paciente;
import br.edu.imepac.common.utils.StatusConsultaEnum;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final ModelMapper modelMapper;
    private final PacienteService pacienteService;
    private final MedicoService medicoService;
    private final ConvenioService convenioService; 

    // ================= CREATE =================
    @Transactional
    public ConsultaDTO criarConsulta(ConsultaCreateRequest dto) {
        
        Paciente paciente = modelMapper.map(pacienteService.buscarPorId(dto.getPacienteId()), Paciente.class);

        
        Medico medico = modelMapper.map(medicoService.buscarPorId(dto.getMedicoId()), Medico.class);

        
        Convenio convenio = null;
        if (dto.getConvenioId() != null) {
            convenio = modelMapper.map(convenioService.buscarPorId(dto.getConvenioId()), Convenio.class);
        }

        
        Consulta consulta = Consulta.builder()
                .paciente(paciente)
                .medico(medico)
                .convenio(convenio)
                .carteiraConvenio(dto.getCarteiraConvenio())
                .dataHora(dto.getDataHora())
                .status(dto.getStatus())
                .build();

        Consulta salva = consultaRepository.save(consulta);
        return mapToDTO(salva);
    }


    // ================= READ =================
    @Transactional(readOnly = true)
    public ConsultaDTO buscarPorId(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada com id: " + id));
        return mapToDTO(consulta);
    }

    @Transactional(readOnly = true)
    public List<ConsultaDTO> listarTodas() {
        return consultaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ================= UPDATE =================
    @Transactional
    public ConsultaDTO atualizarConsulta(Long id, ConsultaUpdateRequest dto) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada com id: " + id));

        if (dto.getPacienteId() != null) {
            Paciente paciente = modelMapper.map(pacienteService.buscarPorId(dto.getPacienteId()), Paciente.class);
            consulta.setPaciente(paciente);
        }

        if (dto.getMedicoId() != null) {
            Medico medico = modelMapper.map(medicoService.buscarPorId(dto.getMedicoId()), Medico.class);
            consulta.setMedico(medico);
        }

        if (dto.getConvenioId() != null) {
            Convenio convenio = modelMapper.map(convenioService.buscarPorId(dto.getConvenioId()), Convenio.class);
            consulta.setConvenio(convenio);
        } else {
            consulta.setConvenio(null);
        }

        if (dto.getCarteiraConvenio() != null)
            consulta.setCarteiraConvenio(dto.getCarteiraConvenio());

        if (dto.getDataHora() != null)
            consulta.setDataHora(dto.getDataHora());

        if (dto.getStatus() != null)
            consulta.setStatus(dto.getStatus());

        Consulta updated = consultaRepository.save(consulta);
        return mapToDTO(updated);
    }

    // ================= CANCEL =================
    @Transactional
    public void cancelarConsulta(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada com id: " + id));
        consulta.setStatus(StatusConsultaEnum.CANCELADA);
        consultaRepository.save(consulta);
    }

    // ================= DELETE =================
    @Transactional
    public void deletarConsulta(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada com id: " + id));
        consultaRepository.delete(consulta);
    }

    // ================= UTIL =================
    private ConsultaDTO mapToDTO(Consulta consulta) {
        return ConsultaDTO.builder()
                .id(consulta.getId())
                .pacienteId(consulta.getPaciente().getId())
                .medicoId(consulta.getMedico().getId())
                .convenioId(consulta.getConvenio() != null ? consulta.getConvenio().getId() : null)
                .dataHora(consulta.getDataHora())
                .status(consulta.getStatus())
                .build();
    }
}
